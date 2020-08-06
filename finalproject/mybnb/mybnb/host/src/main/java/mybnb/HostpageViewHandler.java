package mybnb;

import mybnb.config.kafka.KafkaProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class HostpageViewHandler {


    @Autowired
    private HostpageRepository hostpageRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenBooked_then_CREATE_1 (@Payload Booked booked) {
        try {
            if (booked.isMe()) {

                System.out.println("Booked " + booked.toJson());

                // view 객체 생성
                Hostpage hostpage = new Hostpage();
                // view 객체에 이벤트의 Value 를 set 함
                hostpage.setRoomId(booked.getRoomId());
                hostpage.setBookId(booked.getId());
                hostpage.setStatus(booked.getStatus());
                // view 레파지 토리에 save
                hostpageRepository.save(hostpage);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @StreamListener(KafkaProcessor.INPUT)
    public void whenBookCanceled_then_UPDATE_1(@Payload BookCanceled bookCanceled) {
        try {
            if (bookCanceled.isMe()) {
                // view 객체 조회
                List<Hostpage> hostpageList = hostpageRepository.findByBookId(bookCanceled.getId());
                for(Hostpage hostpage : hostpageList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    hostpage.setStatus(bookCanceled.getStatus());
                    // view 레파지 토리에 save
                    hostpageRepository.save(hostpage);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}