package pl.qamar.sfgpetclinic.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.util.UriTemplate;
import pl.qamar.sfgpetclinic.model.Owner;
import pl.qamar.sfgpetclinic.model.Pet;
import pl.qamar.sfgpetclinic.model.PetType;
import pl.qamar.sfgpetclinic.services.PetService;
import pl.qamar.sfgpetclinic.services.VisitService;

import java.net.URI;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(MockitoExtension.class)
public class VisitControllerTest {

    private final static String PETS_CREATE_OR_UPDATE_VISIT_FORM = "pets/createOrUpdateVisitForm";
    private final static String REDIRECT_OWNERS = "redirect:/owners/{ownerId}";
    private final static String YET_ANOTHER_VISIT_DESCRIPTION = "yet another visit description";

    @Mock
    PetService petService;

    @Mock
    VisitService visitService;

    @InjectMocks
    VisitController visitController;

    private MockMvc mockMvc;

    private final UriTemplate visistsUrlTemplate = new UriTemplate("/owners/{ownerId}/pets/{petId}/visits/new");
    private final Map<String, String> urlVariables = new HashMap<>();
    private URI visitsUrl;

    @BeforeEach
    void setUp() {
        Long petId = 1L;
        Long ownerId = 1L;
        when(petService.findById(anyLong()))
                .thenReturn(Pet.builder()
                        .id(petId)
                        .birthDate(LocalDate.of(2018, 11, 13))
                        .name("Cutie")
                        .visits(new HashSet<>())
                        .owner(Owner.builder()
                                .id(ownerId)
                                .lastName("Doe")
                                .firstName("Joe")
                                .build())
                        .petType(PetType.builder()
                                .name("Dog").build())
                        .build()
                );

        urlVariables.clear();
        urlVariables.put("ownerId", ownerId.toString());
        urlVariables.put("petId", petId.toString());
        visitsUrl = visistsUrlTemplate.expand(urlVariables);

        mockMvc = MockMvcBuilders
                .standaloneSetup(visitController)
                .build();
    }

    @Test
    void initNewVisitForm() throws Exception {
        mockMvc.perform(get(visitsUrl))
                .andExpect(status().isOk())
                .andExpect(view().name(PETS_CREATE_OR_UPDATE_VISIT_FORM));
    }

    @Test
    void processNewVisitForm() throws Exception {
        mockMvc.perform(post(visitsUrl)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("date", "2018-11-13")
                .param("description", YET_ANOTHER_VISIT_DESCRIPTION))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name(REDIRECT_OWNERS))
                .andExpect(model().attributeExists("visit"));
    }
}
