# Activity

**onCreate(**): Đây là hàm bắt buộc phải được implement, được gọi đầu tiên một lần duy nhất khi hệ thống khởi tạo Activity, callback này chỉ được gọi lại khi hệ thống xóa Activity đi hoặc khi chuyển trạng thái giữa 2 chế độ màn hình (**Landscape** và **Portrait**). Vì được gọi đầu tiên và một lần duy nhất nên phương thức **setContentView()** sẽ được gọi ở hàm này, tùy vào logic của Activity, một số logic cũng sẽ được cho vào hàm này.

**onStart()**: Hàm này được gọi khi giao diện được hiển thị nhưng chưa tương tác được với user.

**onResume()**: Khi hệ thống gọi đến callback này, có nghĩa là các View, Component,… của Activity đã khởi tạo xong, đây là trạng thái mà app có thể tương tác với user. Activity sẽ ở trạng thái này cho đến khi có một Activity hoặc app khác được gọi chẳng hạn như có cuộc gọi đến, hoặc tắt màn hình điện thoại.

**onPause()**: Callback này sẽ được gọi tới đầu tiên và ngay lập tức khi user thoát khỏi Activity (Activity chưa bị destroy). Tức là Activty không còn nằm ở foreground nữa nhưng vẫn có thể nhìn thấy đc Activity trên màn hình. **onPause()** thường được sử dụng khi Activity đang không được focus bởi user, một số tính năng trong đó sẽ được tạm dừng hoặc duy trì ở một mức độ nào đó để chờ cho user quay lại. Một số lý do mà bạn nên sử dụng **onPause()**:

        -  Một số sự kiện làm cho Activity bị gián đoạn. Đây là trường hợp xảy ra phổ biến nhất.
        - Từ Android 7.0 (API 24) trở lên, Android có chức năng đa nhiệm (Multi Window). Khi chạy trong chế độ này ở bất kì thời điểm nào cũng chỉ có 1 app được focus bởi user, hệ thống sẽ tạm dừng tất cả các app khác.
        - Khi bị một Activity khác đè lên (ví dụ như một dialog). Activity hiện tại sẽ không tương tác với user, tuy nhiên vẫn được nhìn thấy trên màn hình.

**onStop()**: Khi Activity không còn được nhìn thấy trên màn hình nữa, Activity sẽ rơi vào trạn thái **onStop()**, lúc này bất kỳ chức năng nào trong Activity cũng có thể bị dừng lại, qua đó app sẽ giải phóng được các tài nguyên không cần thiết khi mà nó không còn hữu dụng với user. Tuy nhiên, trong một số trường hợp **onStop()** cũng được dùng để lưu trữ dữ liệu.

**onRestart()**: Phương thức callback này gọi khi activity đã **stoped**, gọi trước khi bắt đầu start lại Activity.
onDestroy: Callback này được gọi khi user thoát hoàn toàn khỏi Activity(nhấn nút back hoặc gọi tới hàm **finish()** của Activity).

## Activity State

**Active/Running**: Khi activity ở foreground và đang tương tác trực tiếp với user.

**Visible/Pause**: Activity không thể tương tác nhưng vẫn được nhìn thấy (bị che khuất không toàn toàn bởi một Activity khác).

**Hidden/Stop**: Activity bị che khuất hoàn toàn bởi một Activity khác, vẫn có thể lưu trữ thông tin những sẽ bị ưu tiên xóa bỏ nếu hệ thống thiếu bộ nhớ

**Destroy**: Khi Activity gọi tới hàm finish() hoặc bị hệ thống xóa bỏ. Khi acitivty đó hiển thị lại với user, nó được khởi tạo lại và khôi phục lại trạng thái trước đó.

#### Cuối cùng, trong Activity Lifecycle có 3 vòng lặp mà chúng ta cần phải nắm rõ:

**Entire lifetime**: Xảy ra giữa lần đầu tiên hệ thống gọi tới onCreate() và onDestroy(). Activity sẽ setup tất cả mọi thứ mang tính cục bộ (global) ở onCreate() và giải phóng toàn bộ ở onDestroy(). Ví dụ một thread download file, thread này có thể tạo ở onCreate() và kết thúc ở onDestroy().

**Visible lifetime**: Xảy ra giữa onStart() và onStop(). Lúc này, Activity hiện hữu trên màn hình và user có thể nhìn thấy nó kể cả khi Activity không nằm trên cùng (foreground) và tương tác với user. Giữa 2 hàm callback này, ta có thể lưu trữ các tài nguyên cần thiết cho việc hiển thị activity đến user. Ví dụ như khi bạn có thể tạo register BroadcastReceiver ở onStart() để theo đõi những thay đổi liên quan đến giao diện người dùng (UI) và unregister nó ở onStop() khi user không còn nhìn thấy những gì được hiển thị

**Foreground lifetime**: Xảy ra giữa onResume() và onPause(). Trong thời gian này, Activiy visible, active và có thể tương tác với user. Một Activity có thể thường xuyên qua lại giữa 2 trạng thái này(khi thiết bị rơi vào trạng thái sleep hoặc bị activity khác che khuất không hoàn toàn).


## Khởi động một activity khác
Để khởi động 1 activity, chúng ta sử dụng Intent
```kotlin
val intent = Intent(this, SecondActivity::class.java)
startActivity(intent)
```
Lúc này, **SecondActivity** sẽ được đưa lên top của BackStack. Activity hiện tại sẽ lần lượt gọi **onPause()** và **onStop()**. Khi **SecondActivity** bị destroy, **FirstActivity** sẽ được đưa lên top và gọi **onRestart()**, **onStart()** và **onResume()**. Ngoài ra, chúng ta cũng có thể gửi và nhận dữ liệu giữa các màn hình thông qua Intent.
**FirstActivity**:
```kotlin
val intent = Intent(this, SecondActivity::class.java)
intent.putExtra("key", "value")
startActivity(intent)
```

**SecondActivity**:
```kotlin
val data = intent.getStringExtra("key")
```

# Lưu trữ và phục hồi trạng thái của Activity
Khi một Activity bị destroy(có thể do nhiều vấn đề như thiếu bộ nhớ, thay đổi hướng xoay màn hình,...), tất cả các thông tin của nó sẽ bị mất. Để giữ lại thông tin đó, chúng ta có thể sử dụng **onSaveInstanceState()** để lưu trữ và **onRestoreInstanceState()** để phục hồi lại thông tin đó.
```kotlin
override fun onSaveInstanceState(outState: Bundle) {
    outState.putString("key", "value")
    super.onSaveInstanceState(outState)
}

override fun onRestoreInstanceState(savedInstanceState: Bundle) {
    super.onRestoreInstanceState(savedInstanceState)
    val data = savedInstanceState.getString("key")
}
```
