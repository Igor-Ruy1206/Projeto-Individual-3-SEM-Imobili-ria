package sptech.school.imobiliaria.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import sptech.school.imobiliaria.model.Imovel;
import sptech.school.imobiliaria.model.TipoImovel;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

/**
 * Acesso a dados via JdbcTemplate puro, conforme exigido pelo enunciado
 * (sem JPA/Hibernate).
 */
@Repository
public class ImovelRepository {

    private final JdbcTemplate jdbcTemplate;

    public ImovelRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String SQL_SELECT_TODOS =
            "SELECT id, logradouro, numero, bairro, cidade, preco, area_m2, tipo FROM imoveis ORDER BY id";

    private static final String SQL_SELECT_POR_ID =
            "SELECT id, logradouro, numero, bairro, cidade, preco, area_m2, tipo FROM imoveis WHERE id = ?";

    private static final String SQL_INSERT =
            "INSERT INTO imoveis (logradouro, numero, bairro, cidade, preco, area_m2, tipo) VALUES (?, ?, ?, ?, ?, ?, ?)";

    private static final String SQL_DELETE = "DELETE FROM imoveis WHERE id = ?";

    public boolean deletarPorId(Long id) {
        int linhasAfetadas = jdbcTemplate.update(SQL_DELETE, id);
        return linhasAfetadas > 0;
    }

    public List<Imovel> buscarTodos() {
        return jdbcTemplate.query(SQL_SELECT_TODOS, this::mapearLinha);
    }

    public Optional<Imovel> buscarPorId(Long id) {
        List<Imovel> resultado = jdbcTemplate.query(SQL_SELECT_POR_ID, this::mapearLinha, id);
        return resultado.stream().findFirst();
    }

    public Imovel salvar(Imovel imovel) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, imovel.getLogradouro());
            ps.setInt(2, imovel.getNumero());
            ps.setString(3, imovel.getBairro());
            ps.setString(4, imovel.getCidade());
            ps.setBigDecimal(5, imovel.getPreco());
            if (imovel.getAreaM2() != null) {
                ps.setDouble(6, imovel.getAreaM2());
            } else {
                ps.setNull(6, java.sql.Types.DOUBLE);
            }
            ps.setString(7, imovel.getTipo().name());
            return ps;
        }, keyHolder);

        Long idGerado = keyHolder.getKey().longValue();
        imovel.setId(idGerado);
        return imovel;
    }

    private Imovel mapearLinha(java.sql.ResultSet rs, int rowNum) throws java.sql.SQLException {
        Double areaM2 = rs.getObject("area_m2") != null ? rs.getDouble("area_m2") : null;
        return new Imovel(
                rs.getLong("id"),
                rs.getString("logradouro"),
                rs.getInt("numero"),
                rs.getString("bairro"),
                rs.getString("cidade"),
                rs.getBigDecimal("preco"),
                areaM2,
                TipoImovel.valueOf(rs.getString("tipo"))
        );
    }


}
