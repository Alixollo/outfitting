package outfitting;

import outfitting.controller.ControllerOrchestrator;
import outfitting.model.MemoryRepository;
import outfitting.model.Repository;
import outfitting.model.entity.cottage.Cottage;
import outfitting.model.entity.outfitting.Outfitting;

public class MainApp {

	public static void main(String[] args) {
		new MainApp();
	}
	
	private Repository<Cottage> cottageRepository;
	private Repository<Outfitting> outfittingRepository;

	public MainApp() {
		this.cottageRepository = new MemoryRepository<Cottage>();
		this.outfittingRepository = new MemoryRepository<Outfitting>();
		
		DataSeeder seeder = new DataSeeder(cottageRepository, outfittingRepository);
		seeder.seedTables();
		
		this.createOrchestrator();
	}

	private void createOrchestrator() {
		new ControllerOrchestrator(this.cottageRepository, this.outfittingRepository);
	}

}
