import android.os.Parcel
import android.os.Parcelable

data class Question(
    val id: Int?,
    val question: String?,
    val optionOne: String?,
    val optionTwo: String?,
    val optionThree: String?,
    val optionFour: String?,
    val correctAnswer: Int?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(question)
        parcel.writeString(optionOne)
        parcel.writeString(optionTwo)
        parcel.writeString(optionThree)
        parcel.writeString(optionFour)
        parcel.writeValue(correctAnswer)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Question> {
        override fun createFromParcel(parcel: Parcel): Question {
            return Question(parcel)
        }

        override fun newArray(size: Int): Array<Question?> {
            return arrayOfNulls(size)
        }
    }
}
