public class HumanReadableTime {
    public static String makeReadable(int seconds) {
        Time time = new Time(seconds);
        return time.toString();
    }

    private static class Time {
        private int hours;
        private int minutes;
        private int seconds;

        public Time(int seconds) {
            this.hours = seconds / 3600;
            this.minutes = (seconds - this.hours * 3600) / 60;
            this.seconds = seconds - (this.hours * 3600 + this.minutes * 60);
        }

        @Override
        public String toString() {
            return String.format("%02d:%02d:%02d", hours, minutes, seconds);
        }
    }
}