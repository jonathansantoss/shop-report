package com.jonathan;

import com.jonathan.dto.ShopDTO;
import com.jonathan.events.ReceiverKafkaMessage;
import com.jonathan.repository.ReportRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class ReceiveKafkaMessageTests {

    @InjectMocks
    private ReceiverKafkaMessage receiveKafkaMessage;
    @Mock
    private ReportRepository reportRepository;

    public ShopDTO getShopDTO() {
        ShopDTO shopDTO = new ShopDTO();
        shopDTO.setStatus("SUCCESS");
        return shopDTO;
    }

    @Test
    public void testProcessShopSuccess() {
        ShopDTO shopDTO = getShopDTO();
        receiveKafkaMessage.listenShopTopic(shopDTO);
        Mockito.verify(reportRepository, Mockito.times(1))
                .incrementShopStatus(shopDTO.getStatus());
    }

}
