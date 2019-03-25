//For move
    private lateinit var mainLayout :ViewGroup
    private var xDelta:Int = 0
    private var yDelta:Int = 0

    private fun onTouchListener(): View.OnTouchListener {
        mainLayout = findViewById(R.id.coorLayout)
        return object: View.OnTouchListener{
            override fun onTouch(view: View?, event: MotionEvent?): Boolean {
                val x = event!!.rawX.toInt()
                val y = event.rawY.toInt()
                when(event.action){
                    MotionEvent.ACTION_DOWN->{
                        val lParams: FrameLayout.LayoutParams = view!!.layoutParams as FrameLayout.LayoutParams

                        xDelta = x- lParams.leftMargin
                        yDelta = y- lParams.topMargin

                    }
                    MotionEvent.ACTION_MOVE->{
                        val lParams: FrameLayout.LayoutParams = view!!.layoutParams as FrameLayout.LayoutParams
                        lParams.leftMargin = x-xDelta
                        lParams.topMargin = y-yDelta
                        lParams.rightMargin = 0
                        lParams.bottomMargin = 0
                        view.layoutParams = lParams

                    }
                }
                mainLayout.invalidate()
                return true
            }

        }
    }



    //documentation
    မင်းရွှေ့ချင်တဲ့ ဟာကို .onTouchListener လုပ် ပီးရင် နောက်က ကွင်းထဲမှာ OnTouchListener Method ကိုထည့်ပါ.
    mainLayout က မင်းရဲ့ Layout ကိုပဲ id ပေးရမှာ
    အာယူအိုခေ