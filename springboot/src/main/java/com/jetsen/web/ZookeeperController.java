package com.jetsen.web;

import com.jetsen.service.ZookeeperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author NICKEL
 */
@RestController
@RequestMapping("/zookeeper")
public class ZookeeperController {
    @Autowired
    private ZookeeperService zookeeperService;

    @GetMapping("/putData")
    public String putData(@RequestParam(value = "isE") boolean isE, @RequestParam(value = "path") String path) {
        try {
            if (isE) {
                zookeeperService.createEphemeral(path, "我就是我".getBytes("UTF-8"));
            } else {
                zookeeperService.create(path, "我就是我".getBytes("UTF-8"));
            }
            return "ok";
        } catch (Exception e) {
            return "not ok";
        }
    }

    @GetMapping("/deletePath")
    public String deletePath(@RequestParam(value = "path") String path) {
        try {
            zookeeperService.delete(path);
            return "ok";
        } catch (Exception e) {
            return "not ok";
        }
    }

    @GetMapping("/transaction")
    public String transaction() {
        try {
            zookeeperService.transaction();
            return "ok";
        } catch (Exception e) {
            return "not ok";
        }
    }

    @GetMapping("/watchNode")
    public String watchNode(@RequestParam(value = "path") String path) {
        try {
            zookeeperService.watchNode(path);
            return "ok";
        } catch (Exception e) {
            return "not ok";
        }
    }

    @GetMapping("/watchNodeChildren")
    public String watchNodeChildren(@RequestParam(value = "path") String path) {
        try {
            zookeeperService.watchNodeChildren(path);
            return "ok";
        } catch (Exception e) {
            return "not ok";
        }
    }

    @GetMapping("/testDistributedLock")
    public String testDistributedLock(@RequestParam(value = "path") String path) {
        try {
            zookeeperService.testDistributedLock(path);
            return "ok";
        } catch (Exception e) {
            return "not ok";
        }
    }
}
