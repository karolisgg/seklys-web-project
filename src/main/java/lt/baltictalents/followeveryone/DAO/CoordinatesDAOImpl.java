package lt.baltictalents.followeveryone.DAO;

import lt.baltictalents.followeveryone.entities.Coordinates;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CoordinatesDAOImpl implements CoordinatesDAO {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void setCoordinates(Coordinates coordinates) {
      Session session = sessionFactory.getCurrentSession();
      session.save(coordinates);
   }

   @Override
   public List<Coordinates> getLastCoordinatesOfAllDevices() {
      Session session = sessionFactory.getCurrentSession();
      return session.createNativeQuery("SELECT * FROM coordinates c WHERE date_time =(SELECT MAX(date_time) FROM coordinates WHERE device_id = c.device_id) ORDER BY device_id", Coordinates.class).list();
   }

   @Override
   public List<Coordinates> getDeviceCoordinatesByTime(String fromDate, String toDate, int deviceId) {
      Session session = sessionFactory.getCurrentSession();
      return session.createNativeQuery("SELECT * FROM coordinates WHERE date_time BETWEEN ? AND ? HAVING device_id = ?", Coordinates.class).setParameter(1, fromDate).setParameter(2, toDate).setParameter(3, deviceId).list();
   }
}