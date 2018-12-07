package lt.baltictalents.followeveryone.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "coordinates")
public class Coordinates {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int id;

   @Column(nullable = false)
   private double latitude;

   @Column(nullable = false)
   private double longitude;

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "device_id")
   private Device device;

   @Column(name = "date_time", nullable = false, columnDefinition="DATETIME")
   @Temporal(TemporalType.TIMESTAMP)
   private Date dateTime;

   public Coordinates() {
   }

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public double getLatitude() {
      return latitude;
   }

   public void setLatitude(double latitude) {
      this.latitude = latitude;
   }

   public double getLongitude() {
      return longitude;
   }

   public void setLongitude(double longitude) {
      this.longitude = longitude;
   }

   public Device getDevice() {
      return device;
   }

   public void setDevice(Device device) {
      this.device = device;
   }

   public Date getDateTime() {
      return dateTime;
   }

   public void setDateTime(Date dateTime) {
      this.dateTime = dateTime;
   }
}
