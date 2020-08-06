package mybnb;

import mybnb.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class PolicyHandler{
    @StreamListener(KafkaProcessor.INPUT)
    public void onStringEventListener(@Payload String eventString){

    }

    @Autowired
    private HostRepository hostRepository;
/*
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverRoomRegistered_Register(@Payload RoomRegistered roomRegistered){

        if(roomRegistered.isMe()){
            System.out.println("##### listener Register : " + roomRegistered.toJson());

            Host hosthistory = new Host();
            hosthistory.setHost(roomRegistered.getName());
            hosthistory.setRoomId(roomRegistered.getId());

            hostRepository.save(hosthistory);
        }
    }
*/


}
