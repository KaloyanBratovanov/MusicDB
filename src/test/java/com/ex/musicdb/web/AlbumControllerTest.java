package com.ex.musicdb.web;

import com.ex.musicdb.model.entities.AlbumEntity;
import com.ex.musicdb.model.entities.ArtistEntity;
import com.ex.musicdb.model.entities.UserEntity;
import com.ex.musicdb.model.entities.enums.Genre;
import com.ex.musicdb.repository.AlbumRepository;
import com.ex.musicdb.repository.ArtistRepository;
import com.ex.musicdb.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class AlbumControllerTest {

    private static final String ALBUM_CONTROLLER_PREFIX = "/albums";

    private long testAlbumId;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ArtistRepository artistRepository;
    @Autowired
    private AlbumRepository albumRepository;


//    @BeforeEach
//    public void setup(){
//        this.init();
//    }



    @Test
    @WithMockUser(value = "admin", roles = {"USER", "ADMIN"})
    void testShouldReturnValidStatusViewNameAndModel() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(
                ALBUM_CONTROLLER_PREFIX + "/details/{id}", testAlbumId
        )).andExpect(status().isOk())
                .andExpect(view().name("details"))
                .andExpect(model().attributeExists("album"));
    }

    private void init(){

        ArtistEntity artistEntity = new ArtistEntity();

        artistEntity.setName("Metallica");
        artistEntity.setCareerInformation("Metallica is  a American heavy metal band");

        artistRepository.save(artistEntity);

        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("admin").setPassword("12345").setFullname("Admin Adminov");

        AlbumEntity albumEntity = new AlbumEntity();

        albumEntity.setName("Reload");
        albumEntity.setImageUrl("https://upload.wikimedia.org/wikipedia/en/d/df/Metallica_-_The_Memory_Remains_cover.jpg");
        albumEntity.setVideoUrl("RDN4awrpPQQ");
        albumEntity.setDescription("\"The Memory Remains\" is a song by American heavy metal band Metallica, with British singer Marianne Faithfull on backing vocals.");
        albumEntity.setCopies(123000);
        albumEntity.setPrice(BigDecimal.valueOf(12));
        //todo year
        albumEntity.setReleaseDate(LocalDate.of(2021, 12, 3).atStartOfDay(ZoneId.systemDefault()).toLocalDate());
        albumEntity.setGenre(Genre.METAL);
        albumEntity.setArtistEntity(artistEntity);
        albumEntity.setUserEntity(userEntity);

        albumRepository.save(albumEntity);

        testAlbumId = albumEntity.getId();

    }



//    @Test
//    @WithMockUser(value = "admin", roles = {"USER", "ADMIN"})
//    void addAlbum() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.post(ALBUM_CONTROLLER_PREFIX + "/add")
//                .param("name", "test album").
//                        param("genre", Genre.METAL.name()).
//                        param("imageUrl", "http://example.com/image.png").
//                        param("videoUrl", "_fKAsvJrFes").
//                        param("description", "Description test").
//                        param("copies", "123333").
//                        param("price", "10").
//                        param("releaseDate", "2000-01-01").
//                        param("artist", "METALLICA").
//                        with(csrf())).
//                andExpect(status().is3xxRedirection());
//
//        Assertions.assertEquals(3, albumRepository.count());
//    }


}
