package lt.baltictalents.followeveryone.DAO;

import lt.baltictalents.followeveryone.entities.Device;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class DeviceDAOImpl implements DeviceDAO {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public List<Device> getAllDevices() {
      Session session = sessionFactory.getCurrentSession();
      return session.createQuery("FROM Device", Device.class).list();
   }

   @Override
   public Device findDevice(String deviceId) {
      Session session = sessionFactory.getCurrentSession();
      List result = session.createQuery("FROM Device d WHERE d.deviceId = ?", Device.class)
            .setParameter(0, deviceId).list();
      if (!result.isEmpty()) {
         return (Device) result.get(0);
      }
      return null;
   }

   @Override
   public void addDevice(Device device) {
      Session session = sessionFactory.getCurrentSession();
      session.save(device);
   }
}
