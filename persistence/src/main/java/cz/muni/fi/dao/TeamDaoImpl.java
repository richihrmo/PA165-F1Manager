package cz.muni.fi.dao;

import cz.muni.fi.entities.Team;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Lucie Kureckova, 445264
 */
@Repository
@Transactional
public class TeamDaoImpl implements TeamDao {

    @PersistenceContext
    private EntityManager em;

    public Team addTeam(Team team) {
        if(team == null){
            throw new IllegalArgumentException("argument is null");
        }
        em.persist(team);
        return team;
    }

    public Team updateTeam(Team team) {
        if(team == null){
            throw new IllegalArgumentException("argument is null");
        }
        em.merge(team);
        return team;
    }

    public boolean deleteTeam(Team team) {
        if(team == null){
            throw new IllegalArgumentException("argument is null");
        }
        em.remove(em.merge(team));
        return true;
    }

    public List<Team> listTeams() {
        return em.createQuery("SELECT t FROM Team t", Team.class).getResultList();
    }

    public Team findTeamByName(String name) {
        if(name == null){
            throw new IllegalArgumentException("argument is null");
        }
        List<Team> result = em.createQuery("SELECT t FROM Team t WHERE t.name = :name", Team.class)
                            .setParameter("name", name).getResultList();
        if(result.isEmpty()){
            return null;
        }
        return result.get(0);
    }

    public Team findTeamById(Long id) {
        if(id == null){
            throw new IllegalArgumentException("argument is null");
        }
        return em.find(Team.class, id);
    }
}
