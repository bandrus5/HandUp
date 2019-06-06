package com.berkeleyandrus.handup;

import java.util.ArrayList;

public class DataStore {
    public static ShelterDTO selectedDTO = null;
    private ArrayList<ShelterDTO> portlandShelters = new ArrayList<>();
    private ArrayList<ShelterDTO> saltLakeShelters = new ArrayList<>();

    DataStore() {
        initShelters();
    }

    private void initShelters() {
        portlandShelters.add(new ShelterDTO("PRM: Burnside", "(503) 906-7690","111 W Burnside St.", "https://portlandrescuemission.org"));
        portlandShelters.add(new ShelterDTO("PRM: The Harbor", "(503) 647-7466", "10336 NE Wygant St.", "https://portlandrescuemission.org"));
        portlandShelters.add(new ShelterDTO("Transition Projects Willamette Center", "(503) 280-4741","5120 SE Milwaukie Ave", "https://www.tprojects.org/portland-shelters/"));
        portlandShelters.add(new ShelterDTO("Dignity Village", "(503) 281-1604","9401 NE Sunderland Ave", "https://dignityvillage.org/"));
        saltLakeShelters.add(new ShelterDTO("Rescue Mission of Salt Lake","(801) 355-1302", "463 400 W", "http://www.rescuesaltlake.org/"));
        saltLakeShelters.add(new ShelterDTO("The Road Home", "(801) 359-4142", "210 S Rio Grande St", "https://www.theroadhome.org/"));
        saltLakeShelters.add(new ShelterDTO("Family Promise", "(801) 961-8622","814 W 800 S", "https://familypromisesaltlake.org/"));
        saltLakeShelters.add(new ShelterDTO("Homeless Outreach Program", "(801) 631-7584","440 400 W", "https://www.voaut.org/"));
    }

    public ArrayList<ShelterDTO> getPortlandShelters() {
        return portlandShelters;
    }
    public ArrayList<ShelterDTO> getSaltLakeShelters() { return saltLakeShelters; }

    public void SelectDTO(ShelterDTO selected) {
        DataStore.selectedDTO = selected;
    }

    public void DeselectDTO() {
        DataStore.selectedDTO = null;
    }
}
