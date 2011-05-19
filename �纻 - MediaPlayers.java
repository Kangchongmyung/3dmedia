package exam.MediaPlayers;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;
import android.media.MediaPlayer;

public class MediaPlayers extends Activity {
	private MediaPlayer mp; // MediaPlayer ��ü�Դϴ�.

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

		// ���� �ҷ����� ��ư�� ���� ������
		Load.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				if(!loadAudio(audioPath.getText().toString())){
					// ����� ������ �ҷ��ɴϴ�.
					Toast // ����� ���� �ҷ����⿡ ������ ���
					.makeText(getApplicationContext(), "���� �ҷ����⿡ �����߽��ϴ�.",
							Toast.LENGTH_LONG).show();
					return;
				}
				audioPath.setEnabled(false); // ���� Path �Է¶��� ��Ȱ��ȭ�մϴ�.
				PlayPause.setEnabled(true);
				Stop.setEnabled(true);
				loopControl.setEnabled(true);
				Load.setEnabled(false);

				Toast
				.makeText(getApplicationContext(), "���� : " + audioPath.getText().toString() +" �ε尡 �Ϸ�Ǿ����ϴ�.", Toast.LENGTH_LONG)
				.show();
			}
		});

		// ��� �ݺ� ���� ���� ��ư�� ���� ������
		loopControl.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				if(loopControl.isChecked()){ // üũ�ڽ��� üũ�ϸ�
					mp.setLooping(true); // �ݺ��� Ȱ��ȭ�մϴ�.
					Toast
					.makeText(getApplicationContext(), "�ݺ� Ȱ��ȭ��", Toast.LENGTH_SHORT)
					.show();
				}else{
					mp.setLooping(false); // �ݺ��� ��Ȱ��ȭ�մϴ�.
					Toast
					.makeText(getApplicationContext(), "�ݺ� ������", Toast.LENGTH_SHORT)
					.show();
				}
			}
		});

		// ���/�Ͻ����� ��ư�� ���� ������
		PlayPause.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				if(PlayPauseAudio()==0){ // �����
					PlayPause.setText("�Ͻ�����"); // ��ư�� ĸ���� �Ͻ������� �����մϴ�.
				}else{ // �Ͻ�������
					PlayPause.setText("���"); // ��ư�� ĸ���� ������� �����մϴ�.
				}
			}

		});

		// ������ư�� ���� ������
		Stop.setOnClickListener(new OnClickListener(){ // ������ư�� ������
			public void onClick(View v){
				mp.stop(); // �����մϴ�.

				// ������ �Ǹ�, Load��ư�� ���� ������ prepare �ؾ� �ϹǷ�, ���������� �ʱ�ȭ�մϴ�.
				audioPath.setEnabled(true); // ��Ȱ��ȭ �Ǿ��� �Է¶��� Ȱ��ȭ�մϴ�.
				PlayPause.setText("���");
				loopControl.setChecked(false);
				PlayPause.setEnabled(false);
				Stop.setEnabled(false);
				loopControl.setEnabled(false);
				Load.setEnabled(true);
			}
		});

	}

	private int PlayPauseAudio(){
		if(!mp.isPlaying()){ // ������� �ƴ϶��
			mp.start(); // ������ ����մϴ�.
			Toast.makeText(getApplicationContext(), "���", Toast.LENGTH_SHORT).show();
			return 0;

		}else{ // ������̶��
			mp.pause(); // �Ͻ������մϴ�.
			Toast.makeText(getApplicationContext(), "�Ͻ� ������", Toast.LENGTH_SHORT).show();
			return 1;
		}

	}

	private boolean loadAudio(String path){ // ����� ������ �ε��մϴ�.
		mp = new MediaPlayer(); // MediaPlayer ��ü�� �����մϴ�.
		try{
			mp.setDataSource(path);
			mp.prepare(); // ������ �غ��մϴ�.
			return true;
		}catch(Exception e){ // ����� ���� �ε忡 �����ϸ�
			Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
			return false; // false�� ��ȯ�մϴ�.
		}
	}

	public void onDestroy(){ // ��Ƽ��Ƽ�� ����� ��
		super.onDestroy();
		if(mp != null)
			mp.release(); // MediaPlayer ��ü�� Release�մϴ�.
		mp = null;
	}

}
