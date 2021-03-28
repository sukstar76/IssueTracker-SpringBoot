package sukstar76.IssueTracker.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/issue")
public class IssueController {
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody()
    public int test() {
        return 1;
    }
}
