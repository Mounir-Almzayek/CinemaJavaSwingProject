import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class Cinema {
    private int id;
    private HashMap<Integer, Movie> movies;

    public Cinema() {
        movies = new HashMap<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public HashMap<Integer, Movie> getMovies() {
        return movies;
    }


    public void setMovies(HashMap<Integer, Movie> movies) {
        this.movies = movies;
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        File file = new File("cinema.txt");
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        Cinema cinema = new Cinema();
        HashMap<Integer, Movie> movies = cinema.getMovies();

        ArrayList<Showtime> showtimeList1 = new ArrayList<>();
        showtimeList1.add(new Showtime(1, LocalDate.of(2024,2,1), Interval.FIRST));
        showtimeList1.add(new Showtime(2, LocalDate.of(2024,2,2), Interval.SECOND));
        showtimeList1.add(new Showtime(3, LocalDate.of(2024,2,3), Interval.THIRD));
        Movie movie1 = new Movie(1, "Interstellar", 2014, "When Earth becomes uninhabitable in the future, a farmer and ex-NASA pilot, Joseph Cooper, is tasked to pilot a spacecraft, along with a team of researchers, to find a new planet for humans.", "2:28","Drama", "MovieImages\\Interstellar.jpg",showtimeList1,15000);
        movies.put(1, movie1);

        ArrayList<Showtime> showtimeList2 = new ArrayList<>();
        showtimeList2.add(new Showtime(1, LocalDate.of(2024,2,1), Interval.SECOND));
        showtimeList2.add(new Showtime(2, LocalDate.of(2024,2,2), Interval.THIRD));
        showtimeList2.add(new Showtime(3, LocalDate.of(2024,2,3), Interval.FIRST));
        Movie movie2 = new Movie(2, "Shutter Island", 2010, "Teddy Daniels and Chuck Aule, two US marshals, are sent to an asylum on a remote island in order to investigate the disappearance of a patient, where Teddy uncovers a shocking truth about the place.","2:18", "Mystery", "MovieImages\\ShutterIsland.jpg",showtimeList2,15000);
        movies.put(2, movie2);

        ArrayList<Showtime> showtimeList3 = new ArrayList<>();
        showtimeList3.add(new Showtime(1, LocalDate.of(2024,2,1), Interval.THIRD));
        showtimeList3.add(new Showtime(2, LocalDate.of(2024,2,2), Interval.FIRST));
        showtimeList3.add(new Showtime(3, LocalDate.of(2024,2,3), Interval.SECOND));
        Movie movie3 = new Movie(3, "John Wick: Chapter 4 ", 2023, "With the price on his head ever increasing, John Wick uncovers a path to defeating The High Table. But before he can earn his freedom, Wick must face off against a new enemy with powerful alliances across the globe and forces that turn old friends into foes.","2:50", "Action", "MovieImages\\JkonWick.jpg",showtimeList3,18000);
        movies.put(3, movie3);

        ArrayList<Showtime> showtimeList4 = new ArrayList<>();
        showtimeList4.add(new Showtime(1, LocalDate.of(2024,2,4),  Interval.FIRST));
        showtimeList4.add(new Showtime(2, LocalDate.of(2024,2,5),  Interval.SECOND));
        showtimeList4.add(new Showtime(3, LocalDate.of(2024,2,6),  Interval.THIRD));
        Movie movie4 = new Movie(4, "Se7en", 1995, "Two homicide detectives are on a desperate hunt for a serial killer whose crimes are based on the \"seven deadly sins\" in this dark and haunting film that takes viewers from the tortured remains of one victim to the next. The seasoned Det. Sommerset researches each sin in an effort to get inside the killer's mind, while his novice partner, Mills, scoffs at his efforts to unravel the case.","2:07", "Action", "MovieImages\\Se7en.jpg",showtimeList4,15000);
        movies.put(4, movie4);

        ArrayList<Showtime> showtimeList5 = new ArrayList<>();
        showtimeList5.add(new Showtime(1, LocalDate.of(2024,2,4), Interval.SECOND));
        showtimeList5.add(new Showtime(2, LocalDate.of(2024,2,5), Interval.THIRD));
        showtimeList5.add(new Showtime(3, LocalDate.of(2024,2,6), Interval.FIRST));
        Movie movie5 = new Movie(5, "The Batman", 2022, "In his second year of fighting crime, Batman uncovers corruption in Gotham City that connects to his own family while facing a serial killer known as the Riddler.","2:57", "Action", "MovieImages\\Batman.jpg",showtimeList5,18000);
        movies.put(5, movie5);

        ArrayList<Showtime> showtimeList6 = new ArrayList<>();
        showtimeList6.add(new Showtime(1, LocalDate.of(2024,2,4), Interval.THIRD));
        showtimeList6.add(new Showtime(2, LocalDate.of(2024,2,5), Interval.FIRST));
        showtimeList6.add(new Showtime(3, LocalDate.of(2024,2,6), Interval.SECOND));
        Movie movie6 = new Movie(6, "Inception", 2010, "A thief who steals corporate secrets through the use of dream-sharing technology is given the inverse task of planting an idea into the mind of a C.E.O., but his tragic past may doom the project and his team to disaster.","2:28", "Sci-Fi", "MovieImages\\Inception.jpg",showtimeList6,16000);
        movies.put(6, movie6);

        ArrayList<Showtime> showtimeList7 = new ArrayList<>();
        showtimeList7.add(new Showtime(1, LocalDate.of(2024,2,7),  Interval.FIRST));
        showtimeList7.add(new Showtime(2, LocalDate.of(2024,2,8),  Interval.SECOND));
        showtimeList7.add(new Showtime(3, LocalDate.of(2024,2,9),  Interval.THIRD));
        Movie movie7 = new Movie(7, "Oppenheimer", 2023, "The story of J. Robert Oppenheimer's role in the development of the atomic bomb during World War II.","3:01", "Action", "MovieImages\\Oppenheimer.jpg",showtimeList7,20000);
        movies.put(7, movie7);

        ArrayList<Showtime> showtimeList8 = new ArrayList<>();
        showtimeList8.add(new Showtime(1, LocalDate.of(2024,2,7), Interval.SECOND));
        showtimeList8.add(new Showtime(2, LocalDate.of(2024,2,8), Interval.THIRD));
        showtimeList8.add(new Showtime(3, LocalDate.of(2024,2,9), Interval.FIRST));
        Movie movie8 = new Movie(8, "The Pursuit of Happiness", 2006, "A struggling salesman takes custody of his son as he's poised to begin a life-changing professional career.","1:57", "Drama", "MovieImages\\Happyness.jpg",showtimeList8,12000);
        movies.put(8, movie8);

        ArrayList<Showtime> showtimeList9 = new ArrayList<>();
        showtimeList9.add(new Showtime(1, LocalDate.of(2024,2,7), Interval.THIRD));
        showtimeList9.add(new Showtime(2, LocalDate.of(2024,2,8), Interval.FIRST));
        showtimeList9.add(new Showtime(3, LocalDate.of(2024,2,9), Interval.SECOND));
        Movie movie9 = new Movie(9, "The Flash", 2023, "When his attempt to save his family inadvertently alters the future, Barry Allen becomes trapped in a reality in which General Zod has returned and there are no Super Heroes to turn to. In order to save the world that he is in and return to the future that he knows, Barry's only hope is to race for his life. But will making the ultimate sacrifice be enough to reset the universe?","2:24", "Action", "MovieImages\\Flash.jpg",showtimeList9,18000);
        movies.put(9, movie9);

        ArrayList<Showtime> showtimeList10 = new ArrayList<>();
        showtimeList10.add(new Showtime(1, LocalDate.of(2024,2,10),  Interval.FIRST));
        showtimeList10.add(new Showtime(2, LocalDate.of(2024,2,11),  Interval.SECOND));
        showtimeList10.add(new Showtime(3, LocalDate.of(2024,2,12),  Interval.THIRD));
        Movie movie10 = new Movie(10, "Fast X", 2023, "Over many missions and against impossible odds, Dom Toretto and his family have outsmarted, out-nerved and outdriven every foe in their path. Now, they confront the most lethal opponent they've ever faced: A terrifying threat emerging from the shadows of the past who's fueled by blood revenge, and who is determined to shatter this family and destroy everything—and everyone—that Dom loves, forever.","2:22", "Action", "MovieImages\\FastX.jpg",showtimeList10,16000);
        movies.put(10, movie10);

        ArrayList<Showtime> showtimeList11 = new ArrayList<>();
        showtimeList11.add(new Showtime(1, LocalDate.of(2024,2,10), Interval.SECOND));
        showtimeList11.add(new Showtime(2, LocalDate.of(2024,2,11), Interval.THIRD));
        showtimeList11.add(new Showtime(3, LocalDate.of(2024,2,12), Interval.FIRST));
        Movie movie11 = new Movie(11, "Prisoners", 2013, "Keller Dover faces a parent's worst nightmare when his 6-year-old daughter, Anna, and her friend go missing. The only lead is an old motorhome that had been parked on their street. The head of the investigation, Detective Loki, arrests the driver, but a lack of evidence forces Loki to release his only suspect. Dover, knowing that his daughter's life is at stake, decides that he has no choice but to take matters into his own hands.","2:33", "Drama", "MovieImages\\Prisoners.jpg",showtimeList11,18000);
        movies.put(11, movie11);

        ArrayList<Showtime> showtimeList12 = new ArrayList<>();
        showtimeList12.add(new Showtime(1, LocalDate.of(2024,2,10), Interval.THIRD));
        showtimeList12.add(new Showtime(2, LocalDate.of(2024,2,11), Interval.FIRST));
        showtimeList12.add(new Showtime(3, LocalDate.of(2024,2,12), Interval.SECOND));
        Movie movie12 = new Movie(12, "Rampage", 2018, "Primatologist Davis Okoye shares an unshakable bond with George, the extraordinarily intelligent, silverback gorilla who has been in his care since birth. But a rogue genetic experiment gone awry mutates this gentle ape into a raging creature of enormous size. To make matters worse, it’s soon discovered there are other similarly altered animals. As these newly created alpha predators tear across North America, destroying everything in their path, Okoye teams with a discredited genetic engineer to secure an antidote, fighting his way through an ever-changing battlefield, not only to halt a global catastrophe but to save the fearsome creature that was once his friend.","1:47", "Action", "MovieImages\\Rampage.jpg",showtimeList12,12000);
        movies.put(12, movie12);

        ArrayList<Showtime> showtimeList13 = new ArrayList<>();
        showtimeList13.add(new Showtime(1, LocalDate.of(2024,2,13),  Interval.FIRST));
        showtimeList13.add(new Showtime(2, LocalDate.of(2024,2,14),  Interval.SECOND));
        showtimeList13.add(new Showtime(3, LocalDate.of(2024,2,15),  Interval.THIRD));
        Movie movie13 = new Movie(13, "Venom", 2018, "Investigative journalist Eddie Brock attempts a comeback following a scandal, but accidentally becomes the host of Venom, a violent, super powerful alien symbiote. Soon, he must rely on his newfound powers to protect the world from a shadowy organization looking for a symbiote of their own.","1:52", "Action", "MovieImages\\Venom.jpg",showtimeList13,13000);
        movies.put(13, movie13);

        ArrayList<Showtime> showtimeList14 = new ArrayList<>();
        showtimeList14.add(new Showtime(1, LocalDate.of(2024,2,13), Interval.SECOND));
        showtimeList14.add(new Showtime(2, LocalDate.of(2024,2,14), Interval.THIRD));
        showtimeList14.add(new Showtime(3, LocalDate.of(2024,2,15), Interval.FIRST));
        Movie movie14 = new Movie(14, "The Shawshank Redemption", 1994, "Framed in the 1940s for the double murder of his wife and her lover, upstanding banker Andy Dufresne begins a new life at the Shawshank prison, where he puts his accounting skills to work for an amoral warden. During his long stretch in prison, Dufresne comes to be admired by the other inmates -- including an older prisoner named Red -- for his integrity and unquenchable sense of hope.","2:22", "Drama", "MovieImages\\ShawShank.jpg",showtimeList14,15000);
        movies.put(14, movie14);

        ArrayList<Showtime> showtimeList15 = new ArrayList<>();
        showtimeList15.add(new Showtime(1, LocalDate.of(2024,2,13),Interval.THIRD));
        showtimeList15.add(new Showtime(2, LocalDate.of(2024,2,14),Interval.FIRST));
        showtimeList15.add(new Showtime(3, LocalDate.of(2024,2,15),Interval.SECOND));
        Movie movie15 = new Movie(15, "The Little Things", 2021, "Officers Deke and Baxter collaborate on a mission to track down a mysterious serial killer, leading the duo to face numerous tests and confront their dark past.","2:08", "Drama", "MovieImages\\TheLittleThings.jpg",showtimeList15,15000);
        movies.put(15, movie15);

        ArrayList<Showtime> showtimeList16 = new ArrayList<>();
        showtimeList16.add(new Showtime(1, LocalDate.of(2024,2,16), Interval.FIRST));
        showtimeList16.add(new Showtime(2, LocalDate.of(2024,2,17), Interval.SECOND));
        showtimeList16.add(new Showtime(3, LocalDate.of(2024,2,18), Interval.THIRD));
        Movie movie16 = new Movie(16, "Talk to Me", 2023, "When a group of friends discover how to conjure spirits using an embalmed hand, they become hooked on the new thrill, until one of them goes too far and unleashes terrifying supernatural forces.","1:35", "Action", "MovieImages\\TalkToMe.jpg",showtimeList16,12000);
        movies.put(16, movie16);

        ArrayList<Showtime> showtimeList17 = new ArrayList<>();
        showtimeList17.add(new Showtime(1, LocalDate.of(2024,2,16), Interval.SECOND));
        showtimeList17.add(new Showtime(2, LocalDate.of(2024,2,17), Interval.THIRD));
        showtimeList17.add(new Showtime(3, LocalDate.of(2024,2,18), Interval.FIRST));
        Movie movie17 = new Movie(17, "Godzilla Minus One", 2023, "In postwar Japan, a new terror rises. Will the devastated people be able to survive... let alone fight back?","2:05", "Action", "MovieImages\\Godzella.jpg",showtimeList17,14000);
        movies.put(17, movie17);

        ArrayList<Showtime> showtimeList18 = new ArrayList<>();
        showtimeList18.add(new Showtime(1, LocalDate.of(2024,2,16), Interval.THIRD));
        showtimeList18.add(new Showtime(2, LocalDate.of(2024,2,17), Interval.FIRST));
        showtimeList18.add(new Showtime(3, LocalDate.of(2024,2,18), Interval.SECOND));
        Movie movie18 = new Movie(18, "La La Land", 2016, "Mia, an aspiring actress, serves lattes to movie stars in between auditions and Sebastian, a jazz musician, scrapes by playing cocktail party gigs in dingy bars, but as success mounts they are faced with decisions that begin to fray the fragile fabric of their love affair, and the dreams they worked so hard to maintain in each other threaten to rip them apart.","2:09", "Comedy", "MovieImages\\Lalaland.jpg",showtimeList18,11000);
        movies.put(18, movie18);

        ArrayList<Showtime> showtimeList19 = new ArrayList<>();
        showtimeList19.add(new Showtime(1, LocalDate.of(2024,2,19), Interval.FIRST));
        showtimeList19.add(new Showtime(2, LocalDate.of(2024,2,20), Interval.SECOND));
        showtimeList19.add(new Showtime(3, LocalDate.of(2024,2,21), Interval.THIRD));
        Movie movie19 = new Movie(19, "Home Alone 2", 1992, "Instead of flying to Florida with his folks, Kevin ends up alone in New York, where he gets a hotel room with his dad's credit card—despite problems from a clerk and meddling bellboy. But when Kevin runs into his old nemeses, the Wet Bandits, he's determined to foil their plans to rob a toy store on Christmas Eve.","2:00", "Comedy", "MovieImages\\HomeAlone.jpg",showtimeList19,14000);
        movies.put(19, movie19);

        ArrayList<Showtime> showtimeList20 = new ArrayList<>();
        showtimeList20.add(new Showtime(1, LocalDate.of(2024,2,19), Interval.SECOND));
        showtimeList20.add(new Showtime(2, LocalDate.of(2024,2,20), Interval.THIRD));
        showtimeList20.add(new Showtime(3, LocalDate.of(2024,2,21), Interval.FIRST));
        Movie movie20 = new Movie(20, "Spider-Man: No Way Home", 2021, "Peter Parker is unmasked and no longer able to separate his normal life from the high-stakes of being a super-hero. When he asks for help from Doctor Strange the stakes become even more dangerous, forcing him to discover what it truly means to be Spider-Man.","2:28", "Action", "MovieImages\\SpiderMan.jpg",showtimeList20,18000);
        movies.put(20, movie20);

        ArrayList<Showtime> showtimeList21 = new ArrayList<>();
        showtimeList21.add(new Showtime(1, LocalDate.of(2024,2,19), Interval.THIRD));
        showtimeList21.add(new Showtime(2, LocalDate.of(2024,2,20), Interval.FIRST));
        showtimeList21.add(new Showtime(3, LocalDate.of(2024,2,21), Interval.SECOND));
        Movie movie21 = new Movie(21, "Godzilla vs. Kong", 2021, "In a time when monsters walk the Earth, humanity’s fight for its future sets Godzilla and Kong on a collision course that will see the two most powerful forces of nature on the planet collide in a spectacular battle for the ages.","1:54", "Action", "MovieImages\\GodzillaVsKong.jpg",showtimeList21,16000);
        movies.put(21, movie21);

        ArrayList<Showtime> showtimeList22 = new ArrayList<>();
        showtimeList22.add(new Showtime(1, LocalDate.of(2024,2,22), Interval.FIRST));
        showtimeList22.add(new Showtime(2, LocalDate.of(2024,2,23), Interval.SECOND));
        showtimeList22.add(new Showtime(3, LocalDate.of(2024,2,24), Interval.THIRD));
        Movie movie22 = new Movie(22, "The Dark Knight", 2008, "When the menace known as the Joker wreaks havoc and chaos on the people of Gotham, Batman must accept one of the greatest psychological and physical tests of his ability to fight injustice.","2:32", "Crime", "MovieImages\\TheDarkNight.jpg",showtimeList22,15000);
        movies.put(22, movie22);

        ArrayList<Showtime> showtimeList23 = new ArrayList<>();
        showtimeList23.add(new Showtime(1, LocalDate.of(2024,2,22), Interval.SECOND));
        showtimeList23.add(new Showtime(2, LocalDate.of(2024,2,23), Interval.THIRD));
        showtimeList23.add(new Showtime(3, LocalDate.of(2024,2,24), Interval.FIRST));
        Movie movie23 = new Movie(23, "Heat", 1995, "Obsessive master thief Neil McCauley leads a top-notch crew on various daring heists throughout Los Angeles while determined detective Vincent Hanna pursues him without rest. Each man recognizes and respects the ability and the dedication of the other even though they are aware their cat-and-mouse game may end in violence.","2:50", "Action", "MovieImages\\Heat.jpg",showtimeList23,17000);
        movies.put(23, movie23);

        ArrayList<Showtime> showtimeList24 = new ArrayList<>();
        showtimeList24.add(new Showtime(1, LocalDate.of(2024,2,22),Interval.THIRD));
        showtimeList24.add(new Showtime(2, LocalDate.of(2024,2,23),Interval.FIRST));
        showtimeList24.add(new Showtime(3, LocalDate.of(2024,2,24),Interval.SECOND));
        Movie movie24 = new Movie(24, "Alexander and the terrible day", 2014, "Alexander's day begins with gum stuck in his hair, followed by more calamities. Though he finds little sympathy from his family and begins to wonder if bad things only happen to him, his mom, dad, brother, and sister all find themselves living through their own terrible, horrible, no good, very bad day.","1:21", "Comedy", "MovieImages\\Alexander.jpg",showtimeList24,11000);
        movies.put(24, movie24);

        ArrayList<Showtime> showtimeList25 = new ArrayList<>();
        showtimeList25.add(new Showtime(1, LocalDate.of(2024,2,25),  Interval.FIRST));
        showtimeList25.add(new Showtime(2, LocalDate.of(2024,2,26),  Interval.SECOND));
        showtimeList25.add(new Showtime(3, LocalDate.of(2024,2,27),  Interval.THIRD));
        Movie movie25 = new Movie(25, "The Hangover", 2009, "When three friends finally come to after a raucous night of bachelor-party revelry, they find a baby in the closet and a tiger in the bathroom. But they can't seem to locate their best friend, Doug – who's supposed to be tying the knot. Launching a frantic search for Doug, the trio perseveres through a nasty hangover to try to make it to the church on time.","1:40", "Comedy", "MovieImages\\Hangover.jpg",showtimeList25,15000);
        movies.put(25, movie25);

        ArrayList<Showtime> showtimeList26 = new ArrayList<>();
        showtimeList26.add(new Showtime(1, LocalDate.of(2024,2,25), Interval.SECOND));
        showtimeList26.add(new Showtime(2, LocalDate.of(2024,2,26), Interval.THIRD));
        showtimeList26.add(new Showtime(3, LocalDate.of(2024,2,27), Interval.FIRST));
        Movie movie26 = new Movie(26, "Cobweb", 2023, "Eight-year-old Peter suffers from hearing a mysterious and continuous sound from inside the wall of his bedroom. However, his parents insist that it is only in his imagination. As Peter's fear grows, he believes that his parents may be hiding a terrible and dangerous secret, leading him to doubt their trust.","1:48", "Horror", "MovieImages\\Cobweb.jpg",showtimeList26,12000);
        movies.put(26, movie26);

        ArrayList<Showtime> showtimeList27 = new ArrayList<>();
        showtimeList27.add(new Showtime(1, LocalDate.of(2024,2,25), Interval.THIRD));
        showtimeList27.add(new Showtime(2, LocalDate.of(2024,2,26), Interval.FIRST));
        showtimeList27.add(new Showtime(3, LocalDate.of(2024,2,27), Interval.SECOND));
        Movie movie27 = new Movie(27, "Miracle in Cell No. 7", 2019, "Separated from his daughter, a father with an intellectual disability must prove his innocence when he is jailed for the death of a commander's child.","2:12", "Drama", "MovieImages\\Turkish2.jpg",showtimeList27,14000);
        movies.put(27, movie27);

        ArrayList<Showtime> showtimeList28 = new ArrayList<>();
        showtimeList28.add(new Showtime(1, LocalDate.of(2024,2,28),  Interval.FIRST));
        showtimeList28.add(new Showtime(2, LocalDate.of(2024,2,29),  Interval.SECOND));
        showtimeList28.add(new Showtime(3, LocalDate.of(2024,3,1),  Interval.THIRD));
        Movie movie28 = new Movie(28, "Paper Lives", 2021, "In the streets of Istanbul, ailing waste warehouse worker Mehmet takes a small boy under his wing and must soon confront his own traumatic childhood.","1:36", "Drama", "MovieImages\\Turkish1.jpg",showtimeList28,13000);
        movies.put(28, movie28);

        ArrayList<Showtime> showtimeList29 = new ArrayList<>();
        showtimeList29.add(new Showtime(1, LocalDate.of(2024,2,28),Interval.SECOND));
        showtimeList29.add(new Showtime(2, LocalDate.of(2024,2,29),Interval.THIRD));
        showtimeList29.add(new Showtime(3, LocalDate.of(2024,3,1) ,Interval.FIRST));
        Movie movie29 = new Movie(29, "Bridge to Terabithia", 2007, "Jesse Aarons trained all summer to become the fastest runner in school. So he's very upset when newcomer Leslie Burke outruns him and everyone else. Despite this and other differences including that she's rich, he's poor, she's a city girl, and he's a country boy the two become fast friends. Together they create Terabithia, a land of monsters, trolls, ogres, and giants where they rule as king and queen.","1:36", "Drama", "MovieImages\\Bridge.jpg",showtimeList29,12000);
        movies.put(29, movie29);

        ArrayList<Showtime> showtimeList30 = new ArrayList<>();
        showtimeList30.add(new Showtime(1, LocalDate.of(2024,2,28), Interval.THIRD));
        showtimeList30.add(new Showtime(2, LocalDate.of(2024,2,29), Interval.FIRST));
        showtimeList30.add(new Showtime(3, LocalDate.of(2024,3,1) , Interval.SECOND));
        Movie movie30 = new Movie(30, "Avatar: The Way of Water", 2022, "Set more than a decade after the events of the first film, learn the story of the Sully family (Jake, Neytiri, and their kids), the trouble that follows them, the lengths they go to keep each other safe, the battles they fight to stay alive, and the tragedies they endure.","3:12", "Action", "MovieImages\\Avatar.jpg",showtimeList30,18000);
        movies.put(30, movie30);

        ArrayList<Showtime> showtimeList31 = new ArrayList<>();
        showtimeList31.add(new Showtime(1, LocalDate.of(2024,3,2),  Interval.FIRST));
        showtimeList31.add(new Showtime(2, LocalDate.of(2024,3,3),  Interval.SECOND));
        showtimeList31.add(new Showtime(3, LocalDate.of(2024,3,4) ,  Interval.THIRD));
        Movie movie31 = new Movie(31, "The Green Mile", 1999, "A supernatural tale set on death row in a Southern prison, where gentle giant John Coffey possesses the mysterious power to heal people's ailments. When the cell block's head guard, Paul Edgecomb, recognizes Coffey's miraculous gift, he tries desperately to help stave off the condemned man's execution.","3:09", "Fantasy", "MovieImages\\GreenMile.jpg",showtimeList31,18000);
        movies.put(31, movie31);

        ArrayList<Showtime> showtimeList32 = new ArrayList<>();
        showtimeList32.add(new Showtime(1, LocalDate.of(2024,3,2), Interval.SECOND));
        showtimeList32.add(new Showtime(2, LocalDate.of(2024,3,3), Interval.THIRD));
        showtimeList32.add(new Showtime(3, LocalDate.of(2024,3,4), Interval.FIRST));
        Movie movie32 = new Movie(32, "Megaboa", 2021, "On a trip to Colombia, a group of college students encounter a fifty-foot boa constrictor, hungry for blood.","1:40", "Action", "MovieImages\\Megaboa.jpg",showtimeList32,14000);
        movies.put(32, movie32);

        ArrayList<Showtime> showtimeList33 = new ArrayList<>();
        showtimeList33.add(new Showtime(1, LocalDate.of(2024,3,2), Interval.THIRD));
        showtimeList33.add(new Showtime(2, LocalDate.of(2024,3,3), Interval.FIRST));
        showtimeList33.add(new Showtime(3, LocalDate.of(2024,3,4), Interval.SECOND));
        Movie movie33 = new Movie(33, "How to Train Your Dragon: The Hidden World", 2019, "As Hiccup fulfills his dream of creating a peaceful dragon utopia, Toothless’ discovery of an untamed, elusive mate draws the Night Fury away. When danger mounts at home and Hiccup’s reign as village chief is tested, both dragon and rider must make impossible decisions to save their kind.","1:44", "Action", "MovieImages\\Dragon.jpg",showtimeList33,12000);
        movies.put(33, movie33);

        ArrayList<Showtime> showtimeList34 = new ArrayList<>();
        showtimeList34.add(new Showtime(1, LocalDate.of(2024,3,5),  Interval.FIRST));
        showtimeList34.add(new Showtime(2, LocalDate.of(2024,3,6),  Interval.SECOND));
        showtimeList34.add(new Showtime(3, LocalDate.of(2024,3,7),  Interval.THIRD));
        Movie movie34 = new Movie(34, "A Beautiful Mind", 2001, "In a decades-spanning biopic, brilliant mathematician John Forbes Nash Jr. makes history in his field as schizophrenia sets in.","2:15", "Drama", "MovieImages\\ABeautifulMind.jpg",showtimeList34,15000);
        movies.put(34, movie34);

        ArrayList<Showtime> showtimeList35 = new ArrayList<>();
        showtimeList35.add(new Showtime(1, LocalDate.of(2024,3,5), Interval.SECOND));
        showtimeList35.add(new Showtime(2, LocalDate.of(2024,3,6), Interval.THIRD));
        showtimeList35.add(new Showtime(3, LocalDate.of(2024,3,7), Interval.FIRST));
        Movie movie35 = new Movie(35, "War for the planet of the Apes", 2017, "Caesar and his apes are forced into a deadly conflict with an army of humans led by a ruthless Colonel. After the apes suffer unimaginable losses, Caesar wrestles with his darker instincts and begins his own mythic quest to avenge his kind. As the journey finally brings them face to face, Caesar and the Colonel are pitted against each other in an epic battle that will determine the fate of both their species and the future of the planet.","2:20", "Drama", "MovieImages\\WarOfThePlanet.jpg",showtimeList35,13000);
        movies.put(35, movie35);

        ArrayList<Showtime> showtimeList36 = new ArrayList<>();
        showtimeList36.add(new Showtime(1, LocalDate.of(2024,3,5), Interval.THIRD));
        showtimeList36.add(new Showtime(2, LocalDate.of(2024,3,6), Interval.FIRST));
        showtimeList36.add(new Showtime(3, LocalDate.of(2024,3,7), Interval.SECOND));
        Movie movie36 = new Movie(36, "Harry Potter and the Sorcerer's Stone", 2001, "An orphaned boy enrolls in a school of wizardry, where he learns the truth about himself, his family and the terrible evil that haunts the magical world.","2:32", "Fantasy", "MovieImages\\HarryPotter1.jpg",showtimeList36,15000);
        movies.put(36, movie36);

        ArrayList<Showtime> showtimeList37 = new ArrayList<>();
        showtimeList37.add(new Showtime(1, LocalDate.of(2024,3,8),  Interval.FIRST));
        showtimeList37.add(new Showtime(2, LocalDate.of(2024,3,9),  Interval.SECOND));
        showtimeList37.add(new Showtime(3, LocalDate.of(2024,3,10),  Interval.THIRD));
        Movie movie37 = new Movie(37, "Harry Potter and the Chamber of Secrets", 2002, "An ancient prophecy seems to be coming true when a mysterious presence begins stalking the corridors of a school of magic and leaving its victims paralyzed.","2:41", "Fantasy", "MovieImages\\HarryPotter2.jpg",showtimeList37,15000);
        movies.put(37, movie37);

        ArrayList<Showtime> showtimeList38 = new ArrayList<>();
        showtimeList38.add(new Showtime(1, LocalDate.of(2024,3,8), Interval.SECOND));
        showtimeList38.add(new Showtime(2, LocalDate.of(2024,3,9), Interval.THIRD));
        showtimeList38.add(new Showtime(3, LocalDate.of(2024,3,10),Interval.FIRST));
        Movie movie38 = new Movie(38, "Harry Potter and the Prisoner of Azkaban", 2004, "Harry Potter, Ron and Hermione return to Hogwarts School of Witchcraft and Wizardry for their third year of study, where they delve into the mystery surrounding an escaped prisoner who poses a dangerous threat to the young wizard.","2:22", "Fantasy", "MovieImages\\HarryPotter3.jpg",showtimeList38,15000);
        movies.put(38, movie38);

        ArrayList<Showtime> showtimeList39 = new ArrayList<>();
        showtimeList39.add(new Showtime(1, LocalDate.of(2024,3,8),  Interval.THIRD));
        showtimeList39.add(new Showtime(2, LocalDate.of(2024,3,9),  Interval.FIRST));
        showtimeList39.add(new Showtime(3, LocalDate.of(2024,3,10), Interval.SECOND));
        Movie movie39 = new Movie(39, "Harry Potter and the Goblet of Fire", 2005, "Harry Potter finds himself competing in a hazardous tournament between rival schools of magic, but he is distracted by recurring nightmares.","2:37", "Fantasy", "MovieImages\\HarryPotter4.jpg",showtimeList39,15000);
        movies.put(39, movie39);

        ArrayList<Showtime> showtimeList40 = new ArrayList<>();
        showtimeList40.add(new Showtime(1, LocalDate.of(2024,3,11),   Interval.FIRST));
        showtimeList40.add(new Showtime(2, LocalDate.of(2024,3,12),   Interval.SECOND));
        showtimeList40.add(new Showtime(3, LocalDate.of(2024,3,13),  Interval.THIRD));
        Movie movie40 = new Movie(40, "Harry Potter and the Order of the Phoenix", 2007, "With their warning about Lord Voldemort's return scoffed at, Harry and Dumbledore are targeted by the Wizard authorities as an authoritarian bureaucrat slowly seizes power at Hogwarts.","2:18", "Fantasy", "MovieImages\\HarryPotter5.jpg",showtimeList40,15000);
        movies.put(40, movie40);

        ArrayList<Showtime> showtimeList41 = new ArrayList<>();
        showtimeList41.add(new Showtime(1, LocalDate.of(2024,3,11), Interval.SECOND));
        showtimeList41.add(new Showtime(2, LocalDate.of(2024,3,12), Interval.THIRD));
        showtimeList41.add(new Showtime(3, LocalDate.of(2024,3,13), Interval.FIRST));
        Movie movie41 = new Movie(41, "Harry Potter and the Half-Blood Prince", 2009, "As Harry Potter begins his sixth year at Hogwarts, he discovers an old book marked as 'the property of the Half-Blood Prince' and begins to learn more about Lord Voldemort's dark past.","2:33", "Fantasy", "MovieImages\\HarryPotter6.jpg",showtimeList41,15000);
        movies.put(41, movie41);

        ArrayList<Showtime> showtimeList42 = new ArrayList<>();
        showtimeList42.add(new Showtime(1, LocalDate.of(2024,3,11),  Interval.THIRD));
        showtimeList42.add(new Showtime(2, LocalDate.of(2024,3,12),  Interval.FIRST));
        showtimeList42.add(new Showtime(3, LocalDate.of(2024,3,13),  Interval.SECOND));
        Movie movie42 = new Movie(42, "Harry Potter and the Deathly Hallows: Part 1", 2010, "As Harry, Ron and Hermione race against time and evil to destroy the Horcruxes, they uncover the existence of the three most powerful objects in the wizarding world: the Deathly Hallows.","2:26", "Fantasy", "MovieImages\\HarryPotter7.jpg",showtimeList42,15000);
        movies.put(42, movie42);

        ArrayList<Showtime> showtimeList43 = new ArrayList<>();
        showtimeList43.add(new Showtime(1, LocalDate.of(2024,3,14), Interval.FIRST));
        showtimeList43.add(new Showtime(2, LocalDate.of(2024,3,15), Interval.SECOND));
        showtimeList43.add(new Showtime(3, LocalDate.of(2024,3,16), Interval.THIRD));
        Movie movie43 = new Movie(43, "Harry Potter and the Deathly Hallows: Part 2", 2011, "Harry, Ron, and Hermione search for Voldemort's remaining Horcruxes in their effort to destroy the Dark Lord as the final battle rages on at Hogwarts.","2:10", "Fantasy", "MovieImages\\HarryPotter8.jpg",showtimeList43,15000);
        movies.put(43, movie43);

        ArrayList<Showtime> showtimeList44 = new ArrayList<>();
        showtimeList44.add(new Showtime(1, LocalDate.of(2024,3,14), Interval.SECOND));
        showtimeList44.add(new Showtime(2, LocalDate.of(2024,3,15), Interval.THIRD));
        showtimeList44.add(new Showtime(3, LocalDate.of(2024,3,16), Interval.FIRST));
        Movie movie44 = new Movie(44, "Fantastic Beasts and Where to Find Them", 2016, "The adventures of writer Newt Scamander in New York's secret community of witches and wizards seventy years before Harry Potter reads his book in school.","2:12", "Fantasy", "MovieImages\\FantasticBeasts1.jpg",showtimeList44,15000);
        movies.put(44, movie44);

        ArrayList<Showtime> showtimeList45 = new ArrayList<>();
        showtimeList45.add(new Showtime(1, LocalDate.of(2024,3,14), Interval.THIRD));
        showtimeList45.add(new Showtime(2, LocalDate.of(2024,3,15), Interval.FIRST));
        showtimeList45.add(new Showtime(3, LocalDate.of(2024,3,16), Interval.SECOND));
        Movie movie45 = new Movie(45, "Fantastic Beasts: The Crimes of Grindelwald", 2018, "In an effort to thwart Grindelwald's plans of raising pure-blood wizards to rule over all non-magical beings, Albus Dumbledore enlists his former student Newt Scamander, who agrees to help, though he's unaware of the dangers that lie ahead. Lines are drawn as love and loyalty are tested, even among the truest friends and family, in an increasingly divided wizarding world.","2:14", "Fantasy", "MovieImages\\FantasticBeasts2.jpg",showtimeList45,15000);
        movies.put(45, movie45);

        ArrayList<Showtime> showtimeList46 = new ArrayList<>();
        showtimeList46.add(new Showtime(1, LocalDate.of(2024,3,17),  Interval.FIRST));
        showtimeList46.add(new Showtime(2, LocalDate.of(2024,3,18),  Interval.SECOND));
        showtimeList46.add(new Showtime(3, LocalDate.of(2024,3,19),  Interval.THIRD));
        Movie movie46 = new Movie(46, "Fantastic Beasts: The Secrets of Dumbledore", 2022, "Professor Albus Dumbledore knows the powerful Dark wizard Gellert Grindelwald is moving to seize control of the wizarding world. Unable to stop him alone, he entrusts Magizoologist Newt Scamander to lead an intrepid team of wizards, witches and one brave Muggle baker on a dangerous mission, where they encounter old and new beasts and clash with Grindelwald's growing legion of followers. But with the stakes so high, how long can Dumbledore remain on the sidelines?","2:22", "Fantasy", "MovieImages\\FantasticBeasts3.jpg",showtimeList46,15000);
        movies.put(46, movie46);

        ArrayList<Showtime> showtimeList47 = new ArrayList<>();
        showtimeList47.add(new Showtime(1, LocalDate.of(2024,3,17),Interval.SECOND));
        showtimeList47.add(new Showtime(2, LocalDate.of(2024,3,18),Interval.THIRD));
        showtimeList47.add(new Showtime(3, LocalDate.of(2024,3,19),Interval.FIRST));
        Movie movie47 = new Movie(47, "Kung Fu Panda", 2008, "When the Valley of Peace is threatened, lazy Po the panda discovers his destiny as the \"chosen one\" and trains to become a kung fu hero, but transforming the unsleek slacker into a brave warrior won't be easy. It's up to Master Shifu and the Furious Five -- Tigress, Crane, Mantis, Viper and Monkey -- to give it a try.","1:30", "Comedy", "MovieImages\\KungFuPanda.jpg",showtimeList47,12000);
        movies.put(47, movie47);

        ArrayList<Showtime> showtimeList48 = new ArrayList<>();
        showtimeList48.add(new Showtime(1, LocalDate.of(2024,3,17), Interval.THIRD));
        showtimeList48.add(new Showtime(2, LocalDate.of(2024,3,18), Interval.FIRST));
        showtimeList48.add(new Showtime(3, LocalDate.of(2024,3,19), Interval.SECOND));
        Movie movie48 = new Movie(48, "Kung Fu Panda 2", 2011, "Po is now living his dream as The Dragon Warrior, protecting the Valley of Peace alongside his friends and fellow kung fu masters, The Furious Five - Tigress, Crane, Mantis, Viper and Monkey. But Po’s new life of awesomeness is threatened by the emergence of a formidable villain, who plans to use a secret, unstoppable weapon to conquer China and destroy kung fu. It is up to Po and The Furious Five to journey across China to face this threat and vanquish it. But how can Po stop a weapon that can stop kung fu? He must look to his past and uncover the secrets of his mysterious origins; only then will he be able to unlock the strength he needs to succeed.","1:31", "Comedy", "MovieImages\\KungFuPanda2.jpg",showtimeList48,12000);
        movies.put(48, movie48);

        ArrayList<Showtime> showtimeList49 = new ArrayList<>();
        showtimeList49.add(new Showtime(1, LocalDate.of(2024,3,20), Interval.FIRST));
        showtimeList49.add(new Showtime(2, LocalDate.of(2024,3,21), Interval.SECOND));
        showtimeList49.add(new Showtime(3, LocalDate.of(2024,3,22), Interval.THIRD));
        Movie movie49 = new Movie(49, "Kung Fu Panda 3", 2016, "While Po and his father are visiting a secret panda village, an evil spirit threatens all of China, forcing Po to form a ragtag army to fight back.","1:35", "Comedy", "MovieImages\\KungFuPanda3.jpg",showtimeList49,12000);
        movies.put(49, movie49);

        ArrayList<Showtime> showtimeList50 = new ArrayList<>();
        showtimeList50.add(new Showtime(1, LocalDate.of(2024,3,20), Interval.SECOND));
        showtimeList50.add(new Showtime(2, LocalDate.of(2024,3,21), Interval.THIRD));
        showtimeList50.add(new Showtime(3, LocalDate.of(2024,3,22), Interval.FIRST));
        Movie movie50 = new Movie(50, "Kung Fu Panda 4", 2024, "Po is gearing up to become the spiritual leader of his Valley of Peace, but also needs someone to take his place as Dragon Warrior. As such, he will train a new kung fu practitioner for the spot and will encounter a villain called the Chameleon who conjures villains from the past.","1:35", "Comedy", "MovieImages\\KungFuPanda4.jpg",showtimeList50,12000);
        movies.put(50, movie50);

        ArrayList<Showtime> showtimeList51 = new ArrayList<>();
        showtimeList51.add(new Showtime(1, LocalDate.of(2024,3,20), Interval.THIRD));
        showtimeList51.add(new Showtime(2, LocalDate.of(2024,3,21), Interval.FIRST));
        showtimeList51.add(new Showtime(3, LocalDate.of(2024,3,22), Interval.SECOND));
        Movie movie51 = new Movie(51, "Aqwaman", 2018, "Once home to the most advanced civilization on Earth, Atlantis is now an underwater kingdom ruled by the power-hungry King Orm. With a vast army at his disposal, Orm plans to conquer the remaining oceanic people and then the surface world. Standing in his way is Arthur Curry, Orm's half-human, half-Atlantean brother and true heir to the throne.","2:33", "Action", "MovieImages\\AqwaMan.jpg",showtimeList51,15000);
        movies.put(51, movie51);

        ArrayList<Showtime> showtimeList52 = new ArrayList<>();
        showtimeList52.add(new Showtime(1, LocalDate.of(2024,3,23),  Interval.FIRST));
        showtimeList52.add(new Showtime(2, LocalDate.of(2024,3,24),  Interval.SECOND));
        showtimeList52.add(new Showtime(3, LocalDate.of(2024,3,25),  Interval.THIRD));
        Movie movie52 = new Movie(52, "The Lord of the Rings: The Fellowship of the Ring", 2001, "A meek Hobbit from the Shire and eight companions set out on a journey to destroy the powerful One Ring and save Middle-earth from the Dark Lord Sauron.","2:58", "Action", "MovieImages\\LOTR1.jpg",showtimeList52,16000);
        movies.put(52, movie52);

        ArrayList<Showtime> showtimeList53 = new ArrayList<>();
        showtimeList53.add(new Showtime(1, LocalDate.of(2024,3,23), Interval.SECOND));
        showtimeList53.add(new Showtime(2, LocalDate.of(2024,3,24), Interval.THIRD));
        showtimeList53.add(new Showtime(3, LocalDate.of(2024,3,25), Interval.FIRST));
        Movie movie53 = new Movie(53, "The Lord of the Rings: The Two Towers", 2002, "While Frodo and Sam edge closer to Mordor with the help of the shifty Gollum, the divided fellowship makes a stand against Sauron's new ally, Saruman, and his hordes of Isengard.","2:59", "Action", "MovieImages\\LOTR2.jpg",showtimeList53,16000);
        movies.put(53, movie53);

        ArrayList<Showtime> showtimeList54 = new ArrayList<>();
        showtimeList54.add(new Showtime(1, LocalDate.of(2024,3,23), Interval.THIRD));
        showtimeList54.add(new Showtime(2, LocalDate.of(2024,3,24), Interval.FIRST));
        showtimeList54.add(new Showtime(3, LocalDate.of(2024,3,25), Interval.SECOND));
        Movie movie54 = new Movie(17, "The Lord of the Rings: The Return of the King", 2003, "Gandalf and Aragorn lead the World of Men against Sauron's army to draw his gaze from Frodo and Sam as they approach Mount Doom with the One Ring.","3:21", "Action", "MovieImages\\LOTR3.jpg",showtimeList54,15000);
        movies.put(54, movie54);



        oos.writeObject(movies);
        HashMap<Integer, Movie> map2 = Movie.readFile();
        map2.forEach((key, value) -> {
            System.out.println("Key: " + key + " Movie name: " + value.getName() + " Description: " + value.getDescription());
        });
    }
}
