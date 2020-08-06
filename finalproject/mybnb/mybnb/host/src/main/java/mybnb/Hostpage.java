package mybnb;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="Hostpage_table")
public class Hostpage {

        @Id
        @GeneratedValue(strategy=GenerationType.AUTO)
        private Long id;
        private String host;
        private Long roomId;
        private String status;
        private String guest;
        private Long bookId;


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
        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }


        public String getGuest() {
        return guest;
    }
        public void setGuest(String guest) {
        this.guest = guest;
    }

        public Long getBookId() {
            return bookId;
        }

        public void setBookId(Long bookId) {
            this.bookId = bookId;
        }

}
