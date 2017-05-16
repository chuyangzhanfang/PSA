import java.util.Comparator;
import java.util.PriorityQueue;

public class SJF {
	//��ҵ�š�����ʱ�䡢����ʱ�䡢��ʼʱ�䡢���ʱ�䡢��תʱ�䡢��Ȩ��תʱ��
	public int id;
	public double arriveTime,runTime,startTime,finishTime,taTime,wtaTime;
	
	//������תʱ�䡢��Ȩ��תʱ��
	public void update(double n){
		finishTime = n;
		taTime = finishTime-arriveTime;
		wtaTime = taTime/runTime;
		startTime=finishTime-runTime;
	}
	
	//������
	public static void main(String[] args){
		//������е�������š����ڵ�ʱ��
		int i = 0;
		double nowTime;
		SJF[] s = new SJF[8];
		//��ʼ��
		init(s);
		
		//���ȶ���
		Comparator<SJF> Order = new Comparator<SJF>(){
			@Override
			public int compare(SJF o1, SJF o2) {
				// TODO Auto-generated method stub
				if(o1.runTime>o2.runTime)return 1;
				else return -1;
			}
		};
		PriorityQueue<SJF> rq = new PriorityQueue<SJF>(11,Order);
		
		//SJF
		rq.add(s[0]);
		i++;
		nowTime = s[0].arriveTime;
		while(!rq.isEmpty()){
			//System.out.println(rq.peek().id);
			nowTime += rq.peek().runTime;			//���µ�ǰʱ��
			rq.poll().update(nowTime);				//������תʱ��ʹ�Ȩ��תʱ��
			
			//�ж�����������еĽ���
			if(i<8&&s[i].arriveTime>nowTime){
				rq.add(s[i]);
				i++;
			}
			else{
				for(int j = i;j<8;j++){
					if(s[j].arriveTime<=nowTime){
						rq.add(s[j]);
						i++;
					}
				}
			}	
		}
		
		System.out.println("��ҵ��|����ʱ��|����ʱ��|��ʼʱ��|���ʱ��|��תʱ��|��Ȩ��תʱ��");
		for(int j=0;j<8;j++){
			System.out.println(s[j].id+"\t"+s[j].arriveTime+"\t"+s[j].runTime+"\t"+s[j].startTime+"\t"+s[j].finishTime+"\t"+String.format("%.2f", s[j].taTime)+"\t"+String.format("%.2f", s[j].wtaTime));
		}
		
	}
	public static void init(SJF[] s){
		for(int i=0;i<8;i++){
			s[i] = new SJF();
			s[i].id = i;
		}
		//s[0].arriveTime = 8; s[0].runTime = 2;
		//s[1].arriveTime = 8.4; s[1].runTime =1;
		//s[2].arriveTime = 8.8; s[2].runTime =0.5;
		//s[3].arriveTime = 9; s[3].runTime = 0.2;
		s[0].arriveTime = 1; s[0].runTime = 7;
		s[1].arriveTime = 5; s[1].runTime =9;
		s[2].arriveTime = 6; s[2].runTime =8;
		s[3].arriveTime = 13; s[3].runTime = 12;
		s[4].arriveTime = 14; s[4].runTime = 15;
		s[5].arriveTime = 17; s[5].runTime =7;
		s[6].arriveTime = 18; s[6].runTime =9;
		s[7].arriveTime = 19; s[7].runTime = 10;
	}
}
