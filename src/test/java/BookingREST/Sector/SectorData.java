package BookingREST.Sector;

public class SectorData {
    public Sector createSector(String sectore){
        Sector sector = new Sector();
        sector.setSector(sectore);
        sector.setName_en("Fishingg");
        sector.setName_ru("Rasha parashaa");
        sector.setName_uk("Рибалкаa");
        sector.setStatus(1);
        return  sector;
    }

    public Sector updateSector(){
        Sector sector = new Sector();
        sector.setName_en("Fishing12");
        sector.setName_ru("Rasha parasha12");
        sector.setName_uk("Рибалка12");
        sector.setStatus(1);
        return  sector;
    }
}
