package net.springbootdemo.controller;

import net.springbootdemo.dto.ClientDto;
import net.springbootdemo.mapper.ClientMapper;
import net.springbootdemo.model.Address;
import net.springbootdemo.model.Client;
import net.springbootdemo.repository.ClientRepository;
import net.springbootdemo.service.ClientService;
import net.springbootdemo.utils.JsonUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(
        value = {
                ClientController.class,
        }
)
public class ClientControllerTest {

    @MockBean
    private ClientService clientService;

    @MockBean
    private ClientRepository clientRepository;

    @Autowired
    protected WebApplicationContext webApplicationContext;

    @Autowired
    protected MockMvc mockMvc;


    @Test
    public void shouldGetAllClients() throws Exception {
        Client firstClient = new Client();
        firstClient.setName("test1");
        firstClient.setInn("123123");
        firstClient.setPhoneNumber("+78999772");

        Client secondClient = new Client();
        secondClient.setName("test2");
        secondClient.setInn("222");
        secondClient.setPhoneNumber("+78122222");

        List<Client> clients = new ArrayList<>(
                Arrays.asList(
                        firstClient, secondClient
                )
        );

        Mockito.doReturn(clients).when(clientService).findAll();

        MvcResult mvcResult = mockMvc.perform(get("/clients")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists())
                .andReturn();

        String result = mvcResult.getResponse().getContentAsString();
        List<ClientDto> res = JsonUtils.readCollectionFromString(result, ClientDto.class);

        Assert.assertEquals(clients.size(), res.size());

    }

    @Test
    public void shouldGetClientById() throws Exception {
        long id = 1L;
        Address address = new Address();
        address.setCity("Simferopol");
        address.setCodeRegion("4324");
        address.setCorpsNumber(123);
        address.setStreet("Lenina");
        address.setHouseNumber(20);

        ClientDto clientDto = new ClientDto();
        clientDto.setId(1L);
        clientDto.setName("test");
        clientDto.setInn("123456");
        clientDto.setPhoneNumber("21132443555");
        clientDto.setAddress(address);

        Mockito.doReturn(ClientMapper.INSTANCE.fromDTO(clientDto))
                .when(clientService).findById(any());

        mockMvc.perform(get("/clients/get/" + id)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$.inn").value(clientDto.getInn()))
                .andExpect(jsonPath("$.address").value(clientDto.getAddress()))
                .andExpect(jsonPath("$.phoneNumber").value(clientDto.getPhoneNumber()))
                .andExpect(jsonPath("$.address.city").value(clientDto.getAddress().getCity()))
                .andExpect(jsonPath("$.address.codeRegion").value(clientDto.getAddress().getCodeRegion()))
                .andExpect(jsonPath("$.address.street").value(clientDto.getAddress().getStreet()))
                .andExpect(jsonPath("$.address.corpsNumber").value(clientDto.getAddress().getCorpsNumber()))
                .andReturn();

    }

    @Test
    public void shouldPostClient() throws Exception {
        Address address = new Address();
        address.setCity("Simferopol");
        address.setCodeRegion("4324");
        address.setCorpsNumber(123);
        address.setStreet("Lenina");
        address.setHouseNumber(20);

        ClientDto clientDto = new ClientDto();
        clientDto.setId(1L);
        clientDto.setName("test");
        clientDto.setInn("123456");
        clientDto.setPhoneNumber("21132443555");
        clientDto.setAddress(address);

        Mockito.doReturn(ClientMapper.INSTANCE.fromDTO(clientDto))
                .when(clientService).saveClient(any());

        mockMvc.perform(post("/clients")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtils.OBJECT_MAPPER.writeValueAsString(clientDto)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$.inn").value(clientDto.getInn()))
                .andExpect(jsonPath("$.address").value(clientDto.getAddress()))
                .andExpect(jsonPath("$.phoneNumber").value(clientDto.getPhoneNumber()))
                .andExpect(jsonPath("$.address.city").value(clientDto.getAddress().getCity()))
                .andExpect(jsonPath("$.address.codeRegion").value(clientDto.getAddress().getCodeRegion()))
                .andExpect(jsonPath("$.address.street").value(clientDto.getAddress().getStreet()))
                .andExpect(jsonPath("$.address.corpsNumber").value(clientDto.getAddress().getCorpsNumber()));
    }

    @Test
    public void shouldUpdateClient() throws Exception {
        long clientIdForUpdate = 1L;

        Address address = new Address();
        address.setCity("Simferopol");
        address.setCodeRegion("4324");
        address.setCorpsNumber(123);
        address.setStreet("Lenina");
        address.setHouseNumber(20);

        ClientDto clientDto = new ClientDto();
        clientDto.setId(1L);
        clientDto.setName("test");
        clientDto.setInn("123456");
        clientDto.setPhoneNumber("21132443555");
        clientDto.setAddress(address);

        Mockito.doReturn(ClientMapper.INSTANCE.fromDTO(clientDto))
                .when(clientService).updateClient(any(), any());

        mockMvc.perform(put("/clients/update/" + clientIdForUpdate)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtils.OBJECT_MAPPER.writeValueAsString(clientDto)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$.inn").value(clientDto.getInn()))
                .andExpect(jsonPath("$.address").value(clientDto.getAddress()))
                .andExpect(jsonPath("$.phoneNumber").value(clientDto.getPhoneNumber()))
                .andExpect(jsonPath("$.address.city").value(clientDto.getAddress().getCity()))
                .andExpect(jsonPath("$.address.codeRegion").value(clientDto.getAddress().getCodeRegion()))
                .andExpect(jsonPath("$.address.street").value(clientDto.getAddress().getStreet()))
                .andExpect(jsonPath("$.address.corpsNumber").value(clientDto.getAddress().getCorpsNumber()));
    }

    @Test
    public void shouldDeleteClient() throws Exception {
        Long idForDeleting = 1L;
        ClientDto clientDto = new ClientDto();
        clientDto.setId(1L);
        clientDto.setName("test");
        clientDto.setInn("123456");
        clientDto.setPhoneNumber("21132443555");

        Mockito.doReturn(ClientMapper.INSTANCE.fromDTO(clientDto))
                .when(clientService).findById(idForDeleting);

        Mockito.doNothing().when(clientService).deleteByIdWithAllOrders(idForDeleting);
        mockMvc.perform(delete("/clients/delete/" + idForDeleting)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldNotDeleteClient() throws Exception {
        Long idForDeleting = 1L;

        Mockito.doNothing().when(clientService).deleteByIdWithAllOrders(idForDeleting);
        mockMvc.perform(delete("/clients/delete/" + idForDeleting)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
}

