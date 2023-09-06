# JWT-Token-Example

Dependency Required :
```
<!-- https://mvnrepository.com/artifact/io.jsonwebtoken/jjwt -->
		<dependency>
			<groupId>io.jsonwebtoken</groupId>
			<artifactId>jjwt-api</artifactId>
			<version>0.11.2</version>
		</dependency>
		<dependency>
			<groupId>io.jsonwebtoken</groupId>
			<artifactId>jjwt-impl</artifactId>
			<version>0.11.2</version>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>io.jsonwebtoken</groupId>
			<artifactId>jjwt-jackson</artifactId>
			<version>0.11.2</version>
			<scope>runtime</scope>
		</dependency>
```

To generate token:
```
private final Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    private static final long EXPIRATION_TIME = 600000; // 1 MIN

    public String generateToken(String userName) {
        Date now = new Date();
        Date expireDate = new Date(now.getTime() + EXPIRATION_TIME);

        return Jwts.builder()
                .setSubject(userName)
                .setIssuedAt(now)
                .setExpiration(expireDate)
                .signWith(secretKey)
                .compact();
    }
```

To validate Token:
```
public String validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token);

            return "valid";
        } catch (ExpiredJwtException expiredJwtException) {
            return "Token Expired";
        } catch (JwtException | IllegalArgumentException e) {
            //failed during parsing
            return "Token Expired";
        }
    }
```
