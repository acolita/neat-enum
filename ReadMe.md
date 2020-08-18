# Neat-Enum

This project aims to provide some neat utility methods for defining enum Java objects.

I see many projects on which people have implemented search through enum values using some loop construct.
This is not efficient. A better way is to define a hashmap on the search key and search the map instead of looping.
 
## Example
Instead of doing this:
```java
enum KindOfFruit {
    APPLE("A652"),
    GRAPE("C686"),
    STRAWBERRY("S012");
    
    private final String code;

    KindOfFruit(final String code) {
        this.code = code;
    }
   
    public KindOfFruit getByCode(final String code) {
        return Stream.of(values()).filter(code::equals).findFirst().orElse(null);
    }

    public String getCode() {
        return this.code;
    }
}
```

One can do this:

```java
enum KindOfFruit {
    APPLE("A652"),
    GRAPE("C686"),
    STRAWBERRY("S012");
    
    private final String code;
    private static final Map<String, KindOfFruit> FRUIT_BY_CODE = NeatEnumBy.getEnumBy(values(), KindOfFruit::getCode); 

    KindOfFruit(final String code) {
        this.code = code;
    }
    
    public KindOfFruit getByCode(final String code) {
        return FRUIT_BY_CODE.getOrDefault(code, null);
    }

    public String getCode() {
        return this.code;
    }

}
```

This may sounds like little improvement, but when ```getCode``` is called itself inside a loop that can make a huge difference.