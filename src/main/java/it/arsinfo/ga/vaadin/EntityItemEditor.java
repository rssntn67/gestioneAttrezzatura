package it.arsinfo.ga.vaadin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.ui.Notification;

import it.arsinfo.ga.model.entity.EntityBase;
import it.arsinfo.ga.model.entity.EntityItems;
import it.arsinfo.ga.service.ServiceItemDao;

public abstract class EntityItemEditor<I extends EntityBase, T extends EntityItems<I>>
        extends Editor<T> {

    private final AddItem<I,T> itemAdd;
    private final CustomButton itemDel;  
    private final CustomButton itemSave; 
    private final CustomGrid<I> itemGrid;
    private final ItemEditor<I> itemEditor;
    private final EntityEditor<T> editor;    
    private final ServiceItemDao<T,I> dao;

    private static final Logger log = LoggerFactory.getLogger(EntityItemEditor.class);

    public EntityItemEditor(ServiceItemDao<T,I> dao,AddItem<I,T> itemAdd, CustomButton itemDel, CustomButton itemSave,CustomGrid<I> itemGrid,
			ItemEditor<I> itemEditor, EntityEditor<T> editor) {
		this.dao=dao;
		this.itemAdd = itemAdd;
		this.itemDel=itemDel;
		this.itemSave=itemSave;
		this.itemGrid = itemGrid;
		this.itemEditor = itemEditor;
		this.editor = editor;

		disableItems();
        editor.setChangeHandler(() -> {
        	disableItems();
        	this.onChange();
        });

        itemDel.setChangeHandler(() -> {
        	try {
				edit(dao.deleteItem(editor.get(), itemEditor.get()));
			} catch (Exception e) {
	            Notification.show(e.getMessage(),
                        Notification.Type.ERROR_MESSAGE);
	            log.error("itemDel: {}", e.getMessage(),e);
			}        	
        });
        
        itemSave.setChangeHandler(() -> {
        	try {
				edit(dao.saveItem(editor.get(), itemEditor.get()));
			} catch (Exception e) {
				Notification.show(e.getMessage(),
                        Notification.Type.ERROR_MESSAGE);
	            log.error("itemSave: {}", e.getMessage(),e);
			}        	
        });
        
        itemAdd.setChangeHandler(() -> {
        	itemEditor.edit(itemAdd.generate());
        	enableItems();
        });
                
        itemGrid.setChangeHandler(() -> {
            if (itemGrid.getSelected() == null) {
            	disableItems();
        	    return;
            }
            itemEditor.edit(itemGrid.getSelected());
            enableItems();
        });

	}
    
	public void edit(T t) {
		editor.edit(t);
		if (t.getId() != null) {
			editor.get().setItems(dao.getItems(t));	
		}
		itemGrid.populate(editor.get().getItems());
		itemAdd.set(editor.get());
    	itemEditor.setVisible(false);
        itemDel.disable();
        itemSave.disable();
        editor.getSave().setEnabled(true);
        editor.getDelete().setEnabled(true);

	}

    public Add<I> getItemAdd() {
		return itemAdd;
	}

	public CustomGrid<I> getItemGrid() {
		return itemGrid;
	}

	public ItemEditor<I> getItemEditor() {
		return itemEditor;
	}

	public EntityEditor<T> getEditor() {
		return editor;
	}

	public CustomButton getItemDel() {
		return itemDel;
	}

	public CustomButton getItemSave() {
		return itemSave;
	}
	
	private void enableItems() {
        itemDel.enable();
        itemSave.enable();
        editor.getSave().setEnabled(false);
        editor.getDelete().setEnabled(false);


	}
	
	private void disableItems() {
        itemEditor.setVisible(false);
        itemDel.disable();
        itemSave.disable();
        editor.getSave().setEnabled(true);
        editor.getDelete().setEnabled(true);		
	}
}
