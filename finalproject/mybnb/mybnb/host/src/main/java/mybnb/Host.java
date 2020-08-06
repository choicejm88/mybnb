package mybnb;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;

@Entity
@Table(name="Host_table")
public class Host {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String host;
    private Long roomId;
    private String approval;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }
    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }
    public String getApproval() {
        return approval;
    }

    public void setApproval(String approval) {
        this.approval = approval;
    }


    @PostPersist
    public void onPostPersist(){
        /*
            // 승인이력 저장한 후 적당한 시간 끌기
            try {
                Thread.currentThread().sleep((long) (400 + Math.random() * 220));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            */

        Verified verified = new Verified();
        BeanUtils.copyProperties(this, verified);
        verified.setApproval(getApproval());
        verified.publishAfterCommit();
    }


}
