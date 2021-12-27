package pl.edu.pjwstk.pro.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.edu.pjwstk.pro.entities.PhotoEntity;
import java.util.List;
import pl.edu.pjwstk.pro.requests.PhotoRequest;
import pl.edu.pjwstk.pro.services.PhotoService;

@RestController
public class PhotoController {
    private final PhotoService photoService;

    public PhotoController(PhotoService photoService) {
        this.photoService = photoService;
    }

    @PreAuthorize("hasAuthority('admin')")
    @PostMapping("/addPhoto")
    public void addPhoto(@RequestBody PhotoRequest photoRequest){
        photoService.savePhoto(photoRequest);
    }
//    @PreAuthorize("hasAuthority('admin')")
//    @PutMapping("/editPhoto/{photoId}")
//    public void editPhoto(@PathVariable Long photoId, @RequestBody PhotoRequest photoRequest){
//        photoService.editPhoto(photoId, photoRequest);
//    }

    @PreAuthorize("hasAuthority('admin')")
    @DeleteMapping("/deletePhoto/{photoId}")
    public void deletePhoto(@PathVariable Long photoId, @RequestBody PhotoRequest photoRequest){
        photoService.deletePhoto(photoId,photoRequest);
    }
    @GetMapping("/getPhoto/{photoId}")
    public PhotoEntity getSinglePhoto(@PathVariable Long photoId){
        return photoService.getSinglePhoto(photoId);
    }
    @GetMapping("/getAllPhotos")
    public List<PhotoEntity> getAllPhoto(){
        return photoService.getAllPhotos();
    }
}
