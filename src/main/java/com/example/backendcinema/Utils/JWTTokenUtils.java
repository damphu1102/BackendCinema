package com.example.backendcinema.Utils;

import com.example.backendcinema.Dto.LoginDto;
import com.example.backendcinema.entity.Account.RoleAccount;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class JWTTokenUtils {
    private static final long EXPIRATION_TIME = 864000000; // 10 days, thời hạn của token
    private static final String SECRET = "123456"; // Chữ ký bí mật
    private static final String PREFIX_TOKEN = "Bearer"; // Ký tự đầu của token
    private static final String AUTHORIZATION = "Authorization"; // Key của token trên header

    // Hàm này dùng để tạo ra token
    public String createAccessToken(LoginDto loginDto) {
        // Tạo giá trị thời hạn token ( bằng thời gian hiện tại + 10 ngày hoặc tuỳ theo )
        Date expirationDate = new Date(System.currentTimeMillis() + EXPIRATION_TIME);
        String token = Jwts.builder()
                .setId(String.valueOf(loginDto.getAccountId())) //set giá trị Id
                .setSubject(loginDto.getUserName()) // set giá trị subject
                .setIssuedAt(new Date())
                .setIssuer("HP CINEMA")
                .setExpiration(expirationDate) // set thời hạn của token
                .signWith(SignatureAlgorithm.HS512, SECRET) // khai báo phương thức mã hóa token và chữ ký bí mật
                .claim("authorities", loginDto.getRoleAccount().name()).compact(); // thêm trường authorities để lưu giá trị phân quyền
        //.claim("user-Agent", loginDto.getUserAgent()).compact();// thêm trường user-Agent để lưu thông tin trình duyệt đang dùng
        return token;
    }

    // Hàm này dùng để giải mã hóa token
    public UsernamePasswordAuthenticationToken parseAccessToken(String token) {
        if (token != null) {
            try {
                token = token.trim(); // xóa khoảng trắng đầu cuối(nếu có)
                Claims claims = Jwts.parser()
                        .setSigningKey(SECRET)
                        .parseClaimsJws(token).getBody(); // Giải mã token
                // Lấy ra các thông tin
                String userName = claims.getSubject();
                RoleAccount role = RoleAccount.valueOf(claims.get("authorities").toString());

                List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
                grantedAuthorities.add(role);

                UsernamePasswordAuthenticationToken userToken = new UsernamePasswordAuthenticationToken(userName, null, grantedAuthorities);
                return userToken;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return null;
            }
        }
        return null;
    }
}
