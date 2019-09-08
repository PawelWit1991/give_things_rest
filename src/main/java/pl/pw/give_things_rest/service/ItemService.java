package pl.pw.give_things_rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.pw.give_things_rest.model.Item;
import pl.pw.give_things_rest.repository.ItemRepository;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public Item save(Item item) { return itemRepository.save(item); }

    public Item update(Item item) {
        return itemRepository.findById(item.getId()).map(itemdb -> {

            if(item.getName()!=null){
                itemdb.setName(item.getName());
            }
            return itemRepository.save(item);
        }).orElse(new Item());
    }

    public void delete(long id) {
        itemRepository.deleteById(id);
    }

    public Item findByItemId(long id){
        return itemRepository.findById(id).orElse(new Item());
    }
}
