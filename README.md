# PRM392 Flood Secure - Flood Rescue & Relief Mobile App

Dự án Mobile (Android) dành cho hệ thống điều phối cứu hộ và quản lý vật tư **Flood Rescue & Relief**, phục vụ môn PRM392. Ứng dụng này hỗ trợ Citizen gửi yêu cầu cứu trợ, Đội Cứu Hộ theo dõi mission, và Coordinator điều phối nguồn lực trực tiếp trên mobile.

## 📱 Tech Stack & Architecture

- **Ngôn ngữ**: Java 11
- **Kiến trúc (Architecture)**: MVVM + Clean Architecture cơ bản
- **Giao diện (UI)**: ViewBinding + Android Material Components
- **Network & API**: Retrofit 2 + Gson
- **State Management**: AndroidX Lifecycle (ViewModel, LiveData)

## 🎨 Design System (Aesthetic)

Ứng dụng sử dụng bộ màu đặc trưng cho lĩnh vực cứu hộ (Flood Relief):
- **Trắng/Xám Sáng** (`colorBackground`): Sạch sẽ, dễ đọc.
- **Xanh Biển Đậm** (`colorPrimary - #0D47A1`): Màu của nước, biểu hiện sự tin cậy, vững chắc.
- **Cam Cứu Hộ** (`colorSecondary - #E65100`): Cảnh báo, hành động khẩn cấp, dễ nhận diện.

## 📂 Cấu trúc thư mục (Packages)

Mọi code logic nằm trong `app/src/main/java/com/example/prm392_flood_secure/`:
- `ui/`: Chứa các màn hình (Activities/Fragments) chia theo module (`auth`, `requests`, `missions`, v.v.). Cũng bao gồm `base/BaseActivity.java` để chuẩn hóa ViewBinding và MVVM.
- `data/`: Chứa `RetrofitClient`, `ApiService`, các class `BaseResponse` dùng để call API trực tiếp từ backend Node.js. Chứa logic lấy dữ liệu (Repository Implementations).
- `domain/`: Chứa Models, Use Cases, Repository Interfaces (Logic nghiệp vụ cốt lõi).
- `di/`: Quản lý tiêm phụ thuộc (Dependency Injection) nếu có.
- `utils/`: Constants, Helpers (VD: format ngày tháng, share preferences...).

## 🚀 Hướng dẫn chạy và code (Dành cho Member)

1. **Pull Code & Sync Gradle:**
   - Clone repo này về máy: `git clone https://github.com/trungnhq31/flood-secure.git`
   - Mở Android Studio -> File -> New -> **Import Project** -> Trỏ tới thư mục vừa clone.
   - Khi AS yêu cầu, hãy chọn **Sync Project with Gradle Files** để tải các dependencies (Retrofit, Lifecycle, Material...).

2. **Quy tắc Code UI (ViewBinding):**
   - Đã bật sẵn **ViewBinding** trong `build.gradle`.
   - Không dùng `findViewById`. Thay vào đó, Activity kế thừa `BaseActivity<T>` và truyền binding class vào hàm `inflateBinding()`.
   - Layout XML sẽ định nghĩa màu động để hỗ trợ **Day/Night mode** (Dùng `?android:attr/colorBackground` hoặc `?attr/colorPrimary`). Không fix cứng màu hex vào file layout.

3. **Quy tắc Code API (Retrofit):**
   - Xem `docs/API_list.md` và `docs/Response.md` trên backend để biết format.
   - Bất kỳ API mới nào đều phải định nghĩa hàm trong `ApiService.java`.
   - Luôn bọc response trả về trong `Call<BaseResponse<T>>`.

## ✨ Môi trường yêu cầu
- Tối thiểu Android API 24 (Min SDK) - Target API 36.
- Android Studio Koala hoặc Ladybug trở lên.
- Java 11+.

> **Lưu ý:** Đừng quên tạo các branch cá nhân khi làm task (VD: `feature/login-screen`, `fix/ui-bug`) và tạo Pull Request thay vì push thẳng lên `main`.
