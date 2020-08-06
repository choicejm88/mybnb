package mybnb.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;

@FeignClient(name="host", url="${api.url.host}")
public interface HostService {

    @RequestMapping(method= RequestMethod.POST, path="/hosts")
    public void verification(@RequestBody Host host);

}

