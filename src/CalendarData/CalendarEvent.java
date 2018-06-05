package CalendarData;

public class CalendarEvent {
    private  String name, description, teamASquad, teamBSquad, stadium;
    private  double ticketPrice;
    private int day, month, year;
    private  int startTimeHour, startTimeMinute;
    private  int endTimeHour, endTimeMinute;
    private int id;

    // Constructors
    public CalendarEvent() {
        this.id = EventQueryProcessor.EVENT_AMOUNT+1;
        EventQueryProcessor.EVENT_AMOUNT++;
    }

    public CalendarEvent(String name, String description, String teamASquad, String teamBSquad, String stadium, double ticketPrice, int day, int month, int year, int startTimeHour, int startTimeMinute, int endTimeHour, int endTimeMinute) {
        this.name = name;
        this.description = description;
        this.teamASquad = teamASquad;
        this.teamBSquad = teamBSquad;
        this.stadium = stadium;
        this.ticketPrice = ticketPrice;
        this.day = day;
        this.month = month;
        this.year = year;
        this.startTimeHour = startTimeHour;
        this.startTimeMinute = startTimeMinute;
        this.endTimeHour = endTimeHour;
        this.endTimeMinute = endTimeMinute;
        this.id = EventQueryProcessor.EVENT_AMOUNT+1;
        EventQueryProcessor.EVENT_AMOUNT++;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getStartTimeHour() {
        return startTimeHour;
    }

    public void setStartTimeHour(int startTimeHour) {
        this.startTimeHour = startTimeHour;
    }

    public int getStartTimeMinute() {
        return startTimeMinute;
    }

    public void setStartTimeMinute(int startTimeMinute) {
        this.startTimeMinute = startTimeMinute;
    }

    public int getEndTimeHour() {
        return endTimeHour;
    }

    public void setEndTimeHour(int endTimeHour) {
        this.endTimeHour = endTimeHour;
    }

    public int getEndTimeMinute() {
        return endTimeMinute;
    }

    public void setEndTimeMinute(int endTimeMinute) {
        this.endTimeMinute = endTimeMinute;
    }

    public String getTeamASquad() {
        return teamASquad;
    }

    public void setTeamASquad(String teamASquad) {
        this.teamASquad = teamASquad;
    }

    public String getTeamBSquad() {
        return teamBSquad;
    }

    public void setTeamBSquad(String teamBSquad) {
        this.teamBSquad = teamBSquad;
    }

    public String getStadium() {
        return stadium;
    }

    public void setStadium(String stadium) {
        this.stadium = stadium;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String toString() {
        return "ID: " + this.getId() + "\r\nName: "  + this.getName()  + "\r\nDescription: " + this.getDescription() + "\r\nStadium: " + this.getStadium() + "\r\nDay: " + this.getDay()  + "\r\nMonth: " + this.getMonth() + "\r\nYear: " + this.getYear() +
                "\r\nStarting at: " + this.getStartTimeHour() + ':' + this.getStartTimeMinute() + "\r\nEnds at: " + this.getEndTimeHour() + ':' + this.getEndTimeMinute();
    }
}
