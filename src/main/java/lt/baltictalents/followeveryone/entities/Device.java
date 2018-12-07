package lt.baltictalents.followeveryone.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "device")
public class Device {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int id;

   @Column(name = "device_id", unique = true, nullable = false)
   private String deviceId;

   @Column(unique = true, nullable = false)
   private String name;

   @Column
   private String description;

   @OneToMany(mappedBy = "device")
   private List<Coordinates> coordinates;

   public Device() {
   }

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public String getDeviceId() {
      return deviceId;
   }

   public void setDeviceId(String deviceId) {
      this.deviceId = deviceId;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public List<Coordinates> getCoordinates() {
      return coordinates;
   }

   public void setCoordinates(List<Coordinates> coordinates) {
      this.coordinates = coordinates;
   }
}
