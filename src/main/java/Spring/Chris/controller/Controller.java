package Spring.Chris.controller;

import Spring.Chris.entity.Cliente;
import Spring.Chris.dto.ClienteDTO;
import Spring.Chris.entity.Produto;
import Spring.Chris.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/cliente/v1/")

public class Controller {
    @Autowired
    Repository repository;

    @PostMapping
    public Cliente create(@RequestBody @Valid Cliente cliente){
        for (Produto p : cliente.getProdutos()){
           p.setPrecoTotal(p.getPrecoUnitario()* p.getQuantidade());
        }
        Cliente clienteSaved = repository.save(cliente);
        return clienteSaved;

    }
    @GetMapping("/{id}")
    @ResponseBody
    public Optional<Cliente> getClienteById(@PathVariable Long id){
        Optional<Cliente> clienteReturned = repository.findById(id);
        return clienteReturned;
    }
    @DeleteMapping("/{id}")
    public String deleteClienteById(@PathVariable Long id){
        try{
            Optional<Cliente> cliente = Optional.of(repository.getById(id));
            if (cliente.isPresent()){
                repository.deleteById(id);
                return "Cliente de "+" deletado com sucesso!";
            }else {

                throw new Exception();
            }
        }catch (Exception e){
            e.printStackTrace();
            return "O cliente de "+id+" não existe para ser deletado!"+
                    "Por favor, entre em contato com o atendimento 999 999 666";
        }
    }
    @GetMapping
    public List<Cliente> listClientes(){
        return repository.findAll();
    }
    @PutMapping("/atualize/{id}")
    public String updateClienteById(@RequestBody ClienteDTO clienteDTO, @PathVariable Long id) {
        Optional<Cliente> velhoCliente = repository.findById(id);

        if (velhoCliente.isPresent()) {
            Cliente cliente = velhoCliente.get();
            cliente.setEndereco(clienteDTO.getEndereco());
            repository.save(cliente);
            return "Cliente de ID "+cliente.getId()+" atualizado com sucesso!";

        } else {
            return "Cliente de ID "+ id + " não existe!";
        }
    }
}