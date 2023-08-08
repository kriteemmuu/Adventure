package com.system.travelmanagement.service.impl;

import com.system.travelmanagement.Entity.Destination;
import com.system.travelmanagement.Pojo.DestinationPojo;
import com.system.travelmanagement.Repo.DestinationRepo;
import com.system.travelmanagement.service.Adddestination;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class AdddestinationImpl implements Adddestination {
    private final DestinationRepo destinationRepo;
    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/images";
    @Override
    public DestinationPojo saveDestination(DestinationPojo destinationPojo) throws IOException {
        Destination destination = new Destination();
        if (destinationPojo.getId() != null){
            destination.setId(destinationPojo.getId());
        }
        destination.setCity(destinationPojo.getCity());
        destination.setCountry(destinationPojo.getCountry());
        destination.setPrice(destinationPojo.getPrice());

        if(destinationPojo.getImage()!=null){
            StringBuilder fileNames = new StringBuilder();
            Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, destinationPojo.getImage().getOriginalFilename());
            fileNames.append(destinationPojo.getImage().getOriginalFilename());
            Files.write(fileNameAndPath, destinationPojo.getImage().getBytes());
            destination.setImage(destinationPojo.getImage().getOriginalFilename());

        }
        destinationRepo.save(destination);
        return new DestinationPojo(destination);
    }


    public List<Destination> listMapping(List<Destination> list){
        Stream<Destination> allRoomsWithImage=list.stream().map(dest ->
                Destination.builder()
                        .id(dest.getId())
                        .city(dest.getCity())
                        .country(dest.getCountry())
                        .price(dest.getPrice())
                        .imageBase64(getImageBase64(dest.getImage()))

                        .build()
        );

        list = allRoomsWithImage.toList();
        return list;
    }
    public String getImageBase64(String fileName) {
        if (fileName!=null) {
            String filePath = System.getProperty("user.dir")+"\\images\\";
            File file = new File(filePath + fileName);
            byte[] bytes;
            try {
                bytes = Files.readAllBytes(file.toPath());
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
            return Base64.getEncoder().encodeToString(bytes);
        }
        return null;
    }
    @Override
    public List<Destination> fetchAll() {
        return listMapping(destinationRepo.findAll());
    }

    @Override
    public void deletebyid(Integer id) {
        destinationRepo.deleteById(id);
    }

    @Override
    public Destination fetchById(Integer id) {
        Destination desti =destinationRepo.findById(id).orElseThrow(()->new RuntimeException("not found"));
        desti=Destination.builder()
                .id(desti.getId())
                .city(desti.getCity())
                .country(desti.getCountry())
                .price(desti.getPrice())
                .imageBase64(getImageBase64(desti.getImage()))
                .build();
        return desti;
    }




}

