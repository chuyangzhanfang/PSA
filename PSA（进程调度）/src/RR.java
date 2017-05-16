import java.util.LinkedList;
import java.util.Queue;

public class RR {
	//���̺š�����ʱ�䡢����ʱ�䡢���ʱ�䡢��תʱ�䡢��Ȩ��תʱ��
	public int id;
	public double arriveTime,runTime,finishTime,taTime,wtaTime;
	//��ǰʣ����������ʱ��
	public double remainingTime;
	
	//������תʱ�䡢��Ȩ��תʱ��
	public void re(double n){
		finishTime = n;
		taTime = finishTime-arriveTime;
		wtaTime = taTime/runTime;
	}
	public static void main(String[] args){
			//������е�������š���תʱ�䡢������
			int i = 0 , span = 1, n = 5;
			//��ǰʱ��
			double  nowTime;
			RR[] s = new RR[n];
			//��ʼ��
			init(s,n);
			//��������
			Queue<RR> q = new LinkedList<RR>();
			q.add(s[0]);
			i++;
			nowTime = s[0].arriveTime;
			while(!q.isEmpty()){
				//ʱ��Ƭ���ڸý��̵ķ���ʱ��
				if(span>=q.peek().remainingTime){
					nowTime += q.peek().remainingTime;
					//�����µĽ���
					for(int j=i;j<n;j++){
						if(s[j].arriveTime<=nowTime){
							q.add(s[j]);
							i++;
						}
					}
					q.poll().re(nowTime);
					//System.out.println(nowTime);
					//��ֹ����ʣ�����ʱ�����ж�
					if(i<n&&q.isEmpty()){
						q.add(s[i]);
						nowTime = q.peek().arriveTime;
						i++;
					}
				}
				else{
					nowTime += span;
					//���·���ʣ��ʱ��
					q.peek().remainingTime -= span;
					//�����½���
					for(int j=i;j<n;j++){
						if(s[j].arriveTime<=nowTime){
							q.add(s[j]);
							i++;
						}
					}
					//������������ĩβ
					q.add(q.poll());
				}	
			}
			System.out.println("���̺�|����ʱ��|����ʱ��|���ʱ��|��תʱ��|��Ȩ��תʱ��");
			for(int j=0;j<n;j++){
				System.out.println(s[j].id+"\t"+s[j].arriveTime+"\t"+s[j].runTime+"\t"+s[j].finishTime+"\t"+s[j].taTime+"\t"+String.format("%.2f", s[j].wtaTime));
			}
			
		}
		public static void init(RR[] s,int n){
			for(int i=0;i<n;i++){
				s[i] = new RR();
				s[i].id = i;
			}
			s[0].arriveTime = 0; s[0].remainingTime = s[0].runTime = 4;
			s[1].arriveTime = 1; s[1].remainingTime = s[1].runTime = 3;
			s[2].arriveTime = 2; s[2].remainingTime = s[2].runTime = 4;
			s[3].arriveTime = 3; s[3].remainingTime = s[3].runTime = 2;
			s[4].arriveTime = 4; s[4].remainingTime = s[4].runTime = 4;
		}
}
