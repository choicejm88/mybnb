package mybnb;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.net.ConnectException;
import java.util.List;

@Entity
@Table(name="Room_table")
public class Room {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String name;
    private Long price;
    private String address;
    private String host;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Long getPrice() {
        return price;
    }
    public void setPrice(Long price) {
        this.price = price;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getHost() {
        return host;
    }
    public void setHost(String host) {
        this.host = host;
    }

    @PostPersist
    public void onPostPersist(){
        // 방등록시 승인확인 트랜잭션을 통합을 위해 호스트승인 서비스 직접 호출
        {
            mybnb.external.Host host = new mybnb.external.Host();
            host.setRoomId(getId());
            host.setHost(getHost());
            host.setApproval("RoomRegisterApproved");

            // mappings goes here
            try {
                RoomApplication.applicationContext.getBean(mybnb.external.HostService.class)
                        .verification(host);
            }catch(Exception e) {
                throw new RuntimeException("방등록 호출 실패입니다.");
            }
        }

        // 승인완료되면 최종적으로 방등록 완료 이벤트 발생
        RoomRegistered roomRegistered = new RoomRegistered();
        BeanUtils.copyProperties(this, roomRegistered);
        roomRegistered.setStatus("roomRegistered");
        roomRegistered.publishAfterCommit();
    }



    @PostRemove
    public void onPostRemove(){
        RoomDeleted roomDeleted = new RoomDeleted();
        BeanUtils.copyProperties(this, roomDeleted);
        roomDeleted.publishAfterCommit();
    }

}
