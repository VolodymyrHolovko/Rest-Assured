package BookingREST.Sector;

public class SectorData {
    public Sector createSector(String sectore){
        Sector sector = new Sector();
        sector.setSector(sectore);
        sector.setName_en("Fishing");
        sector.setName_ru("Rasha parasha");
        sector.setName_uk("Рибалка");
        sector.setStatus(1);
        return  sector;
    }

    public Sector updateSector(){
        Sector sector = new Sector();
        sector.setName_en("Fishing1");
        sector.setName_ru("Rasha parasha1");
        sector.setName_uk("Рибалка1");
        sector.setStatus(1);
        return  sector;
    }
}
