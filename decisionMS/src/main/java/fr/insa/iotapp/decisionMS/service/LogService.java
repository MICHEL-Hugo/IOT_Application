/**
 * Ce fichier définit le service `LogService`, qui est responsable de la gestion
 * des logs dans la base de données. Ce service offre des méthodes pour récupérer,
 * ajouter, mettre à jour et supprimer des logs dans la base de données via JDBC.
 * 
 * Fonctionnalités principales :
 * - Récupérer tous les logs depuis la base de données via la méthode `getAllLogs()`.
 * - Récupérer un log spécifique par son ID via la méthode `getLogById(int id)`.
 * - Ajouter un nouveau log dans la base de données via la méthode `addLog(logModel log)`.
 * - Mettre à jour un log existant dans la base de données via la méthode `updateLog(logModel log)`.
 * - Supprimer un log de la base de données via la méthode `deleteLog(int id)`.
 * 
 * Le service utilise l'objet `JdbcTemplate` pour interagir avec la base de données et
 * exécuter les requêtes SQL nécessaires. Il utilise également un `RowMapper` pour mapper
 * les résultats des requêtes SQL sur des objets `logModel`.
 * 
 * Structure :
 * - Annotation `@Service` : indique que cette classe est un service Spring.
 * - `JdbcTemplate` : permet d'exécuter des requêtes SQL dans la base de données.
 * - `RowMapper<logModel>` : interface utilisée pour mapper les résultats des requêtes SQL
 *   en objets de type `logModel`.
 * 
 * Auteur : MICHEL Hugo et MASSON Alexandre
 */

package fr.insa.iotapp.decisionMS.service;

import fr.insa.iotapp.decisionMS.model.logModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class LogService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final class LogRowMapper implements RowMapper<logModel> {
        @Override
        public logModel mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new logModel(
                    rs.getString("timestamp"),
                    rs.getInt("outside_temperature"),
                    rs.getInt("inside_temperature"),
                    rs.getString("window_state")
            );
        }
    }

    public List<logModel> getAllLogs() {
        String sql = "SELECT * FROM logs";
        return jdbcTemplate.query(sql, new LogRowMapper());
    }

    public logModel getLogById(int id) {
        String sql = "SELECT * FROM logs WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new LogRowMapper(), id);
    }

    public void addLog(logModel log) {
        String sql = "INSERT INTO logs (timestamp, outside_temperature, inside_temperature, window_state) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, log.getTimestamp(), log.getOutsideTemperature(), log.getInsideTemperature(), log.getWindowState());
    }

    public void updateLog(logModel log) {
        String sql = "UPDATE logs SET timestamp = ?, outside_temperature = ?, inside_temperature = ?, window_state = ? WHERE id = ?";
        jdbcTemplate.update(sql, log.getTimestamp(), log.getOutsideTemperature(), log.getInsideTemperature(), log.getWindowState(), log.getId());
    }

    public void deleteLog(int id) {
        String sql = "DELETE FROM logs WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
