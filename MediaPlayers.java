package exam.MediaPlayers;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;
import android.media.MediaPlayer;

public class MediaPlayers extends Activity {
	private MediaPlayer mp; // MediaPlayer 객체입니다.

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		final EditText audioPath = (EditText)findViewById(R.id.srcEdt);
		final CheckBox loopControl = (CheckBox)findViewById(R.id.setLoop);
		final Button PlayPause = (Button)findViewById(R.id.PlayPause);
		final Button Stop = (Button)findViewById(R.id.Stop);
		final Button Load = (Button)findViewById(R.id.load);

		// 파일 불러오기 버튼에 대한 리스너
		Load.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				if(!loadAudio(audioPath.getText().toString())){
					// 오디오 파일을 불러옵니다.
					Toast // 오디오 파일 불러오기에 실패한 경우
					.makeText(getApplicationContext(), "파일 불러오기에 실패했습니다.",
							Toast.LENGTH_LONG).show();
					return;
				}
				audioPath.setEnabled(false); // 파일 Path 입력란을 비활성화합니다.
				PlayPause.setEnabled(true);
				Stop.setEnabled(true);
				loopControl.setEnabled(true);
				Load.setEnabled(false);

				Toast
				.makeText(getApplicationContext(), "파일 : " + audioPath.getText().toString() +" 로드가 완료되었습니다.", Toast.LENGTH_LONG)
				.show();
			}
		});

		// 재생 반복 여부 선택 버튼에 대한 리스너
		loopControl.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				if(loopControl.isChecked()){ // 체크박스에 체크하면
					mp.setLooping(true); // 반복을 활성화합니다.
					Toast
					.makeText(getApplicationContext(), "반복 활성화됨", Toast.LENGTH_SHORT)
					.show();
				}else{
					mp.setLooping(false); // 반복을 비활성화합니다.
					Toast
					.makeText(getApplicationContext(), "반복 해제됨", Toast.LENGTH_SHORT)
					.show();
				}
			}
		});

		// 재생/일시정지 버튼에 대한 리스너
		PlayPause.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				if(PlayPauseAudio()==0){ // 재생시
					PlayPause.setText("일시정지"); // 버튼의 캡션을 일시정지로 변경합니다.
				}else{ // 일시정지시
					PlayPause.setText("재생"); // 버튼의 캡션을 재생으로 변경합니다.
				}
			}

		});

		// 정지버튼에 대한 리스너
		Stop.setOnClickListener(new OnClickListener(){ // 정지버튼을 누르면
			public void onClick(View v){
				mp.stop(); // 정지합니다.

				// 정지가 되면, Load버튼을 눌러 파일을 prepare 해야 하므로, 설정값들을 초기화합니다.
				audioPath.setEnabled(true); // 비활성화 되었던 입력란을 활성화합니다.
				PlayPause.setText("재생");
				loopControl.setChecked(false);
				PlayPause.setEnabled(false);
				Stop.setEnabled(false);
				loopControl.setEnabled(false);
				Load.setEnabled(true);
			}
		});

	}

	private int PlayPauseAudio(){
		if(!mp.isPlaying()){ // 재생중이 아니라면
			mp.start(); // 파일을 재생합니다.
			Toast.makeText(getApplicationContext(), "재생", Toast.LENGTH_SHORT).show();
			return 0;

		}else{ // 재생중이라면
			mp.pause(); // 일시정지합니다.
			Toast.makeText(getApplicationContext(), "일시 정지됨", Toast.LENGTH_SHORT).show();
			return 1;
		}

	}

	private boolean loadAudio(String path){ // 오디오 파일을 로드합니다.
		mp = new MediaPlayer(); // MediaPlayer 객체를 생성합니다.
		try{
			mp.setDataSource(path);
			mp.prepare(); // 파일을 준비합니다.
			return true;
		}catch(Exception e){ // 오디오 파일 로드에 실패하면
			Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
			return false; // false를 반환합니다.
		}
	}

	public void onDestroy(){ // 액티비티가 종료될 때
		super.onDestroy();
		if(mp != null)
			mp.release(); // MediaPlayer 객체를 Release합니다.
		mp = null;
	}

}
