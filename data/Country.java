package data;
public enum Country {
    UNITED_KINGDOM{
        @Override
        public String toString() {
            return "Соединенное Королевство";
        }
    },
    SOUTH_KOREA{
        @Override
        public String toString() {
            return "Южная Корея";
        }
    },
    NORTH_KOREA{
        @Override
        public String toString() {
            return "Северная Корея";
        }
    };
}