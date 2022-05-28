package outfitting;

import outfitting.model.Region;
import outfitting.model.Repository;
import outfitting.model.entity.cottage.Cottage;
import outfitting.model.entity.outfitting.Outfitting;

public class DataSeeder {
	
	private Repository<Cottage> cottageRepository;
	private Repository<Outfitting> outfittingRepository;

	public DataSeeder(Repository<Cottage> cottageRepository, Repository<Outfitting> outfittingRepository) {
		this.cottageRepository = cottageRepository;
		this.outfittingRepository = outfittingRepository;
	}
	
	public void seedTables() {	
		this.seedOutfittingRepository();
		this.seedCottageRepository();
	}
	
	private void seedCottageRepository() {
		this.addCottageToRepository(new Cottage("Le Pavillon des Pécheurs", 8, 4, 100), 1);
		this.addCottageToRepository(new Cottage("La Maison des Chasseurs", 15, 3, 150), 1);
		this.addCottageToRepository(new Cottage("Le Chalet du Lac Noir", 8, 2, 80), 2);
		this.addCottageToRepository(new Cottage("Le Chalet des Loutres", 5, 4, 120), 3);
		this.addCottageToRepository(new Cottage("Le Chalet du Grand Pin", 4, 5, 125), 3);
	}
	
	private void seedOutfittingRepository() {
		this.outfittingRepository.add(new Outfitting("Pourvoirie des Gens Heureux", Region.GASPESIE,
				"418-564-6426", "GensHeureux@pourvoirie.ca", "Jacques Tremblay", "418-683-1243", "JTremb@outlook.com"));
		
		this.outfittingRepository.add(new Outfitting("Pourvoirie Dubois", Region.CENTRE_QUEBEC,
				"819-955-2084", "dubois@pourvoirie.ca", "", "", ""));

		this.outfittingRepository.add(new Outfitting("Pourvoirie de l'Oie Blanche", Region.MONTREAL,
				"438-863-3922", "blancheoie@pourvoirie.ca", "Claire Lagacé", "418-344-8882", "CLagace@gmail.com"));
	}
	
	private void addCottageToRepository(Cottage cottage, int outfittingId) {
		cottage.setOutfitting(this.outfittingRepository.getById(outfittingId));
		this.cottageRepository.add(cottage);
	}

}
