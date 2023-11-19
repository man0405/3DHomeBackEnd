package com.example.backend.controllers.information;


import com.example.backend.services.information.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/test")
@PreAuthorize("hasRole('USER')")
public class TestController {

    @Autowired
    private JwtService jwtService;


    @GetMapping("/anon")
    public String anonEndPoint() {
        return "everyone can see this";
    }

    @GetMapping("/users")
    public String usersEndPoint() {
        return "ONLY users can see this";
    }

    @GetMapping("/admins")
    public String adminsEndPoint() {
        return "ONLY admins can see this";
    }


    @GetMapping("/test")
    public void testH(@RequestHeader("Authorization") String bear){

            System.out.println("bear : " + bear);
            String jwt =  bear.substring(7);
            //Bearer eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoibWFuMTFAYWRtMmluLmNvbSIsImlhdCI6MTcwMDMxNjMxNSwiZXhwIjoxNzAwMzE5OTE1fQ.s-WGI-gz41wxFeBwZ6ii2pUJ-NWAU74suKWxav79CwU

            System.out.println("jwt : " + jwt);
//            System.out.println("jwt extract "+ jwtService.extractId(jwt));
            Long id = Long.parseLong(jwtService.extractId(jwt));
        System.out.println("Id : " + id);


    }
}
