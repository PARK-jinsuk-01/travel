package com.example.travel.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
// import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.ModelAttribute;
// import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// import org.springframework.web.bind.annotation.ResponseBody;

// import com.example.travel.model.Hotplace;
import com.example.travel.model.Hotplaces;
import com.example.travel.repository.HotplaceRepository;



@Controller
public class MapController {
   @Autowired
    HotplaceRepository hotplaceRepository;
    @GetMapping("/map")
    // @ResponseBody
    public String map() {
        return "map/map";
    }
    @GetMapping("/map/hotplace")
    public String hotplace(Model model) {
        List<Hotplaces> hotplaces = hotplaceRepository.findAll();
        model.addAttribute("hotplaces", hotplaces);
        return "map/hotplace";
    }
    @RestController
    @RequestMapping("/api/hotplaces")
    public class HotplaceController {

        @Autowired
        private HotplaceRepository hotplaceRepository;

        @GetMapping
        public List<Hotplaces> getAllHotplaces() {
            return hotplaceRepository.findAll();
        }
    }
}