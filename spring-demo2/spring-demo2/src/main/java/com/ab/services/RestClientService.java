package com.ab.services;

import com.ab.models.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class RestClientService {

    private RestTemplate restTemplate;

    @Autowired
    public RestClientService(RestTemplateBuilder restTemplateBuilder){
        this.restTemplate =restTemplateBuilder.build();
    }

    public String insertEmployee(Employee employee){
        HttpEntity<Employee> entity = new HttpEntity<>(employee);
        return restTemplate.exchange("http://localhost:8081/api/add", HttpMethod.POST,entity,String.class).getBody();
    }

    public List<Employee> getEmployee(){
        return restTemplate.exchange("http://localhost:8081/api/find", HttpMethod.GET,null,List.class).getBody();
    }

    public Optional<Employee> getEmployeeById(Integer id){
        String url = "http://localhost:8081/api/find";
        //Optional<Employee> emp = restTemplate.getForObject(url,Optional.class);
        return restTemplate.getForObject(url+"/"+id+"",Optional.class);
    }

    public void deleteEmp(Integer id){
        String url = "http://localhost:8081/api/delete";
        HashMap<String,Integer> param = new HashMap<>();
        param.put("id",id);
        restTemplate.delete(url+"/"+id+"",param);
    }

}
