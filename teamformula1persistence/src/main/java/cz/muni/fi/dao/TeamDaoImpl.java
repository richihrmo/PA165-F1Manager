package cz.muni.fi.dao;

import cz.muni.fi.entities.Team;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author Lucie Kureckova, 445264
 */
@Repository
public class TeamDaoImpl implements TeamDao {

    @PersistenceContext
    private EntityManager em;

    public void addTeam(Team team) {
        em.persist(team);
    }

    public void updateTeam(Team team) {
        em.merge(team);
    }

    public void deleteTeam(Team team) {
        em.remove(em.merge(team));
    }

    public List<Team> listTeams() {
        return em.createQuery("SELECT t FROM Team t", Team.class).getResultList();
    }

    public Team findTeam(String name) {
        List<Team> result = em.createQuery("SELECT t FROM Team t WHERE t.name = :name", Team.class)
                            .setParameter("name", name).getResultList();
        if(result.isEmpty()){
            return null;
        }
        return result.get(0);
    }

    public Team findTeam(Long id) {
        return em.find(Team.class, id);
    }
}
