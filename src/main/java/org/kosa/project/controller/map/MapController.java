package org.kosa.project.controller.map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class MapController {
    private final String apiKey;

    public MapController(@Value("${kakao.map.api.key}") String apiKey) {
        this.apiKey = apiKey;
    }
    @GetMapping("/map")
    public String showMap(Model model) {
        model.addAttribute("apiKey", apiKey);
        return "map";
    }

    @PostMapping("/getUserLocation")
    @ResponseBody
    public Map<String, Object> getUserLocation(@RequestBody Map<String, Double> location) {
        double latitude = location.get("latitude");
        double longitude = location.get("longitude");

        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "위치 정보가 성공적으로 수신되었습니다.");
        response.put("latitude", latitude);
        response.put("longitude", longitude);

        return response;
    }
}
