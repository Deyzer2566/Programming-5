package storage;
import java.time.ZonedDateTime;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import data.*;

public class Database {
    private PriorityQueue<StudyGroup> collection;
    private ZonedDateTime creationTime;

    public Database(){
        collection = new PriorityQueue<>();
        creationTime = ZonedDateTime.now();
    }

    /**
     * Добавляет группу в базу
     * @param newGroup группа, которую нужно добавить в коллекцию
     * @return true, если группа успешно добавлена в базу, false, если нет
     */
    public boolean add(StudyGroup newGroup){
        long id = 0;
        for(StudyGroup group: getCollection().stream().sorted((o1, o2) -> o1.getId()>o2.getId()?1:0).toList()
        ){
            if(group.getId() == id){
                id++;
            }
        }
        newGroup.changeId(id);
        return collection.add(newGroup);
    }

    public boolean put(StudyGroup newGroup) throws ThereIsGroupWithThisIdException{
        for(StudyGroup group: getCollection()){
            if(group.getId() == newGroup.getId()){
                throw new ThereIsGroupWithThisIdException("Группа с указанным ID уже есть!");
            }
        }
        return collection.add(newGroup);
    }

    /**
     * Возвращает группу по её id
     * @param id ИД группы
     * @return StudyGroup группа с указаным id, если группа с таким id есть в коллекции, null, если группа не найдена
     */
    public StudyGroup getGroup(long id){
        for(StudyGroup group: collection){
            if(group.getId() == id){
                return group;
            }
        }
		return null;
    }

    /**
    * Удаляет группу из базы по её id
    * @param id ИД группы
    * @throws GroupDidNotFound если группа с указанным id не найдена
	* @return true, если группа удалена, false, если нет
    */
    public boolean remove(long id) throws GroupDidNotFound{
		StudyGroup group = getGroup(id);
		if(group == null){
			throw new GroupDidNotFound("Группа не найдена!");
		}
		return collection.remove(group);
    }

    /**
     * Очищает базу
     */
    public void clear(){
        collection.clear();
    }


    /**
     * Удаляет первую группу
     * @return StudyGroup удаленная группа
     */
    public StudyGroup removeHead() throws GroupDidNotFound{
        StudyGroup group = null;
        try {
            group = collection.remove();
        }catch(NoSuchElementException e){
            throw new GroupDidNotFound("База пуста!");
        }
        return group;
    }

    /**
     * Возвращает коллекцию групп
     * @return коллекция
     */
	public PriorityQueue<StudyGroup> getCollection(){
		return this.collection;
	}

    @Override
    public String toString() {
        return "Тип: "+collection.getClass().getTypeName()+"\n"+
                "Дата инициализации: "+creationTime+"\n"+
                "Количество групп: "+collection.size();
    }

    /**
     *
     * @return максимальную группу (сортировка по названию)
     */
    public StudyGroup getMax(){
        StudyGroup max = null;
        for(StudyGroup group: collection){
            if(group.compareTo(max)>0){
                max = group;
            }
        }
        return max;
    }

    /**
     *
     * @return размер базы
     */
    public int getSize(){
        return collection.size();
    }
	
	/**
	* Обновляет группу по заданому id
	* @param id ИД группы
	* @param group обновленная группа
	* @throws GroupDidNotFound если группа с указанным id не найдена
	*/
    public void update(long id, StudyGroup group) throws GroupDidNotFound{
		remove(id);
		group.changeId(id);
		add(group);
    }
}
