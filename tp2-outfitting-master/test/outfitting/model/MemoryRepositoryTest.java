package outfitting.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import outfitting.exception.IllegalSearchException;
import outfitting.exception.InvalidIdException;
import outfitting.model.entity.EntityMock;

class MemoryRepositoryTest {
	
	private final EntityMock ANY_ENTITY = new EntityMock();
	private MemoryRepository<EntityMock> anEntityMemoryRepository;

	@BeforeEach
	public void setUpAnEntityMemoryRepository(){
		this.anEntityMemoryRepository = new MemoryRepository<EntityMock>();
	}

	@Test
	public void createEntityMemoryRepository_shouldInitializeListOfEntities() {
		assertNotNull(this.anEntityMemoryRepository.searchAll());
	}

	@Test
	public void add_shouldAddEntityToListOfEntities() {
		
		this.anEntityMemoryRepository.add(ANY_ENTITY);
		
		assertTrue(this.anEntityMemoryRepository.searchAll().contains(ANY_ENTITY));
	}
	
	@Test
	public void size_whenListIsEmpty_shouldReturnZero() {		
		assertEquals(0, this.anEntityMemoryRepository.size());
	}
	
	@Test
	public void size_whenListContainsAnElement_shouldReturnNumberOfElements() {
		
		this.anEntityMemoryRepository.add(ANY_ENTITY);
		
		assertEquals(1, this.anEntityMemoryRepository.size());
	}

	@Test
	public void searchById_whenIdIsValid_shouldReturnEntity() {
		this.anEntityMemoryRepository.add(ANY_ENTITY);
		
		assertEquals(ANY_ENTITY, this.anEntityMemoryRepository.getById(ANY_ENTITY.getId()));
	}
	
	@Test
	public void searchById_whenIdIsInvalid_shouldReturnInvalidIdException() {
		final int ANY_INVALID_ID = -4;

		assertThrows(InvalidIdException.class, () -> this.anEntityMemoryRepository.getById(ANY_INVALID_ID));
	}
	
	@Test
	public void search_whenFilterIsNull_shouldReturnIllegalSearchException() {
		assertThrows(IllegalSearchException.class, () -> this.anEntityMemoryRepository.search(null));
	}
	
	@Test
	public void remove_whenRepositoryContainsNoEntityWithSpecifiedID_shouldThrowInvalidIdException() {
		assertThrows(InvalidIdException.class, () -> this.anEntityMemoryRepository.remove(7564));
	}
	
	@Test
	public void remove_whenRepositoryContainsEntityWithSpecifiedID_shouldRemoveEntity() {
		this.anEntityMemoryRepository.add(ANY_ENTITY);
		
		this.anEntityMemoryRepository.remove(ANY_ENTITY.getId());
		
		assertFalse(this.anEntityMemoryRepository.searchAll().contains(ANY_ENTITY));
	}

	@Test
	public void search_whenRepositoryContainsNoEntity_shouldReturnEmptyCollection() {
		assertTrue(this.anEntityMemoryRepository.search(new EntityFilterMock()).isEmpty());
	}
	
	@Test
	public void search_whenRepositoryContainsEntities_shouldReturnCollection() {
		this.anEntityMemoryRepository.add(ANY_ENTITY);
		
		assertFalse(this.anEntityMemoryRepository.search(new EntityFilterMock()).isEmpty());
	}
	
	@Test
	public void addObserver_shouldAddObserver() {
		EntityRepositoryObserverMock mock = new EntityRepositoryObserverMock();
		
		this.anEntityMemoryRepository.addObservers(mock);
		
		this.anEntityMemoryRepository.add(new EntityMock());
		assertTrue(mock.notifyHasBeenCalled);
	}
	
	@Test
	public void removeObserver_shouldRemoveObserver() {
		EntityRepositoryObserverMock mock = new EntityRepositoryObserverMock();
		this.anEntityMemoryRepository.addObservers(mock);
		
		this.anEntityMemoryRepository.removeObservers(mock);
		
		this.anEntityMemoryRepository.add(new EntityMock());
		assertFalse(mock.notifyHasBeenCalled);
	}
	
}
