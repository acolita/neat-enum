# Neat-Enum

This project aims to provide some neat utility methods for defining enum Java objects.

I see many projects on which people have implemented search through enum values using some loop construct.
This is not efficient. A better way is to define a hashmap on the search key and search the map instead of looping.

## Use it within your project

For using library in your project, just add this to your ```pom.xml```.

```xml
<dependency>
  <groupId>br.com.acolita</groupId>
  <artifactId>neat-enum</artifactId>
  <version>0.3</version>
</dependency>
```

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
    private static final NeatEnumGetter<KindOfFruit, String> FRUIT_BY_CODE = new NeatEnumGetter<>(KindOfFruit.class, KindOfFruit::getCode); 

    KindOfFruit(final String code) {
        this.code = code;
    }
    
    public KindOfFruit getByCode(final String code) {
        return FRUIT_BY_CODE.orNull(code);
    }

    public String getCode() {
        return this.code;
    }
}
```

This may sounds like little improvement, but when ```getCode``` is called itself inside a loop that can make a huge difference.

## Composing methods

If you need, you can compose your getter method with another method.
Bellow, an example on how you can compare your String keys in a case-insensitive way.

```java
enum KindOfFruit {
    APPLE("A652"),
    GRAPE("C686"),
    STRAWBERRY("S012");
    
    private final String code;
    private static final NeatEnumGetter<KindOfFruit, String> FRUIT_BY_CODE = new NeatEnumGetter<>(KindOfFruit.class, NeatCompose.compose(KindOfFruit::getCode, String::toLowerCase)); 

    KindOfFruit(final String code) {
        this.code = code;
    }
    
    public KindOfFruit getByCode(final String code) {
        return FRUIT_BY_CODE.orNull(code.toLowerCase());
    }

    public String getCode() {
        return this.code;
    }
}
```