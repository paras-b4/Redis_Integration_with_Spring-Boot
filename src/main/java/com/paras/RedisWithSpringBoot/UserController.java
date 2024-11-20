package com.paras.RedisWithSpringBoot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class UserController
{
    @Autowired
    private UserDao dao;
    @PostMapping("/create")
    public User create(@RequestBody User user){
        return dao.save(user);

    }
    @GetMapping("/{userId}")
    public User getUser(@PathVariable String userId){
        return dao.get(userId);
    }
    // here we are converting map into list .
//    @GetMapping
//    public List<User> getAll(){
//        Map<Object,Object> all=dao.findAll();
//        Collection<Object> values=all.values();
//        List<User> collect=values.stream().map(value-> (User)value).collect(Collectors.toList());
//        return collect;
//    }
    @GetMapping
    public Map<Object,Object> getAll(){
        return dao.findAll();
    }
    @DeleteMapping("/{userId}")
    public void delete(@PathVariable String userId){
         dao.delete(userId);
    }

}
