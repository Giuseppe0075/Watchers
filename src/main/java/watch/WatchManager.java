package watch;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class WatchManager {
    // TEST CLASS NOT AN ACTUAL CLASS DO NOT USE

    static Watch watch1 = new Watch("1", "Orologio 1", 1000D, "Bello, molto bello",
            List.of("watchpic/1/pic1.png","watchpic/1/pic2.png", "watchpic/1/pic3.png" ));
    static Watch watch2 = new Watch("2", "Orologio 2", 3000D, "Bello, abbastanza bello",
            List.of("watchpic/2/pic1.png","watchpic/2/pic2.png", "watchpic/2/pic3.png" ));
    static Watch watch3 = new Watch("3", "Orologio 3", 5000D, "Bello, poco bello",
            List.of("watchpic/3/pic1.png","watchpic/3/pic2.png", "watchpic/3/pic3.png" ));

    public final static List<Watch> watches = List.of(watch1, watch2, watch3);

    public static Watch retriveWatchbyID(String id){
        Optional<Watch> watchOptional = watches.stream().filter(watch -> watch.getId().equals(id)).findFirst();
        return watchOptional.orElse(null);
    }
}
