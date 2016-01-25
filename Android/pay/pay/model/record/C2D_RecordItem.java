package pay.model.record;

import c2d.util.misc.C2D_MiscUtil;

/**
 * ��Ϸ��¼�� �������������ԣ���¼ID����¼��������¼���ݡ����м�¼ID���û�����ģ�
 * ����һ���ַ������ݹ�����Ϸ��¼ʱ��������"$"���зָ����������֣�
 * ���Ƿֱ������������ͼ�¼���ݣ���Ϸ��¼�������ݸ������ޣ�����֮����"$"���зָ���
 * ���������¼����ʱ�����ܴ����ظ���ID���������Ӽ�¼����ȡ��Ӧ�ļ�¼���������⡣
 * ����ԴӼ�¼������һ����ȡ��Ϣ��ʹ��readXXX()��Ҳ���Զ�һ���д����Ϣ����¼���ݣ�
 * ʹ��writeXXX()�� ���ж�ȡ��д����������ݶ����ڲ��� �ַ�������ʽ������
 * ���ڼ�¼ID��������
 * ��Ϊƽ̨���ƣ���¼IDȡ[1-3]Ϊ�ã���ΪZSJ֧�����6����GDXC֧�����3����
 * SQ�������ޣ���Լ��Ϊ6����
 * ���������ַ���
 * GDXCҪ�����ݲ������ַ�'|'��'&'��ZSJҪ�����ݲ�����'#'������������
 * SQ�е�����������û�����Ƶ��ַ����ܽ���˵�����������Լ�ʹ�õķָ���'$'��
 * ����4���ָ�������ʹ�ã��ֱ���'|'��'&'��'#','$'��
 * ���⣬'+'��GDXC�ᱻ�滻�ɿհס�
 * ����ȫ�ּ�¼��
 * ȫ�ּ�¼����Ϸ��Ψһ��¼����������Ϸ�����������������GDXC��SQ����֧��ȫ�ּ�¼��
 * ZSJ֧��ȫ�ּ�¼������ȫ�ּ�¼�в��ܺ������ġ�
 * 
 * @author AndrewFan
 * 
 */
public class C2D_RecordItem
{
	/** ��¼����֮��ķָ��� */
	private static final char SEP = '$';
	private static final char ForbiddenList[]={'|','&','#','%'};
	private int m_recordID=1;
	private String m_desc = "";
	private String m_content="";
	private int m_readPos;
	/**
	 * �����µ���Ϸ��¼
	 * @param recordID ��¼ID����1��ʼ��һ�㲻������������ȡ[1-3]Ϊ�á�
	 */
	public C2D_RecordItem(int recordID)
	{
		setRecordID(recordID);
		setDescription(recordID+"");
	}
	/**
	 * �����µ���Ϸ��¼
	 * @param recordID ��¼ID
	 * @param desc ��¼����
	 */
	public C2D_RecordItem(int recordID, String desc)
	{
		setRecordID(recordID);
		setDescription(desc);
	}
	/**
	 * ���ü�¼ID
	 * @param recordID
	 */
	private void setRecordID(int recordID)
	{
		m_recordID = recordID;
		if(m_recordID<1||m_recordID>6)
		{
			C2D_MiscUtil.log("<Warnning>", "��¼ID����");
		}
	}

	/**
	 * ��������
	 * 
	 * @param desc
	 */
	public void setDescription(String desc)
	{
		if (desc != null)
		{
			m_desc = desc;
		}
	}

	/**
	 * ��ȡ����
	 * 
	 * @return ����
	 */
	public String getDescription()
	{
		return m_desc;
	}

	/**
	 * ��ȡ��¼����
	 * 
	 * @return ��¼����
	 */
	public String getContent()
	{
		if(m_content!=null&&m_content.length()>0)
		{
			return m_content.toString();
		}
		return null;
	}

	/**
	 * ���ü�¼���ݣ���������֮�󣬼�¼λ�û����á�
	 * 
	 * @param content
	 */
	public void setContent(String content)
	{
		if (content != null)
		{
			m_content=content;
			resetReadPos();
		}
	}
	
	/**
	 * ��ü�¼ID
	 * @return ��¼ID
	 */
	public int getRecordID()
	{
		return m_recordID;
	}
	/**
	 * ����¼���ݴ��л������ת�����ַ�������¼����+�ָ���+��¼���ݡ�
	 * 
	 * @return ���л��ַ���
	 */
	public String serializeOut()
	{
		//����Ƿ��зǷ��ַ�
		if(cotainsFBC(m_desc,ForbiddenList))
		{
			C2D_MiscUtil.log("<Error>","��¼�������зǷ��ַ�");
		}
		if(cotainsFBC(m_content,ForbiddenList))
		{
			C2D_MiscUtil.log("<Error>","��¼�������зǷ��ַ�");
		}
		return m_desc + SEP + m_content;
	}
	/**
	 * ����ַ����Ƿ��зǷ��ַ�
	 * @param word �������ַ���
	 * @param forbiddenList ��ֹ�ַ��б�
	 * @return �Ƿ���
	 */
	private boolean cotainsFBC(String word,char forbiddenList[])
	{
		if(m_content==null)
		{
			return false;
		}
		for (int i = 0; i < forbiddenList.length; i++)
		{
			if(word.indexOf(forbiddenList[i])>=0)
			{
				return true;
			}
		}
		return false;
	}
	/**
	 * �����ݴ��л����룬ת�������ü�¼��������¼����
	 * 
	 * @return �Ƿ�����ɹ�
	 */
	public boolean serializeIn(String data)
	{
		if(data==null)
		{
			return false;
		}
		int index=data.indexOf(SEP);
		if(index<0)
		{
			return false;
		}
		String s1=data.substring(0,index);
		String s2=data.substring(index+1,data.length());
		setDescription(s1);
		setContent(s2);
		return true;
	}
	/**
	 * д��������ֵ
	 * 
	 * @param value
	 *            ������ֵ
	 */
	public void writeInt(int value)
	{
		m_content+=value;
		m_content+=SEP;
	}

	/**
	 * ��ȡ������ֵ�������ȡʧ�ܣ��᷵��-1���������쳣��Ϣ
	 * 
	 * @return ������ֵ
	 */
	public int readInt()
	{
		int value = -1;
		String chunck = nextChunck();
		if (chunck != null)
		{
			try
			{
				value = Integer.parseInt(chunck);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return value;
	}

	/**
	 * д��������ֵ����
	 * 
	 * @param value
	 *            ������ֵ����
	 */
	public void writeInts(int value[])
	{
		if (value == null)
		{
			return;
		}
		writeInt(value.length);
		for (int i = 0; i < value.length; i++)
		{
			writeInt(value[i]);
		}
	}

	/**
	 * ��ȡ������ֵ����
	 * 
	 * @param ��������
	 *            �������ȡʧ���򷵻ؿ�
	 */
	public int[] readInts()
	{
		int length = readInt();
		if (length < 0)
		{
			return null;
		}
		int[] value = new int[length];
		for (int i = 0; i < length; i++)
		{
			value[i] = readInt();
		}
		return value;
	}
	/**
	 * д����������ֵ
	 * 
	 * @param value
	 *            ��������ֵ
	 */
	public void writeLong(long value)
	{
		m_content+=value;
		m_content+=SEP;
	}

	/**
	 * ��ȡ��������ֵ�������ȡʧ�ܣ��᷵��-1���������쳣��Ϣ
	 * 
	 * @return ��������ֵ
	 */
	public long readLong()
	{
		long value = -1;
		String chunck = nextChunck();
		if (chunck != null)
		{
			try
			{
				value = Long.parseLong(chunck);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return value;
	}

	/**
	 * д����������ֵ����
	 * 
	 * @param value
	 *            ��������ֵ����
	 */
	public void writeLongs(long value[])
	{
		if (value == null)
		{
			return;
		}
		writeInt(value.length);
		for (int i = 0; i < value.length; i++)
		{
			writeLong(value[i]);
		}
	}

	/**
	 * ��ȡ��������ֵ����
	 * 
	 * @param ����������
	 *            �������ȡʧ���򷵻ؿ�
	 */
	public long[] readLongs()
	{
		int length = readInt();
		if (length < 0)
		{
			return null;
		}
		long[] value = new long[length];
		for (int i = 0; i < length; i++)
		{
			value[i] = readLong();
		}
		return value;
	}
	/**
	 * д����������ֵ
	 * 
	 * @param value
	 *           ��������ֵ
	 */
	public void writeFloat(float value)
	{
		m_content+=value;
		m_content+=SEP;
	}

	/**
	 * ��ȡ��������ֵ�������ȡʧ�ܣ��᷵��-1���������쳣��Ϣ
	 * 
	 * @return ��������ֵ
	 */
	public float readFloat()
	{
		float value = -1;
		String chunck = nextChunck();
		if (chunck != null)
		{
			try
			{
				value = Float.parseFloat(chunck);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return value;
	}

	/**
	 * д����������ֵ����
	 * 
	 * @param value
	 *            ��������ֵ����
	 */
	public void writeFloats(float value[])
	{
		if (value == null)
		{
			return;
		}
		writeInt(value.length);
		for (int i = 0; i < value.length; i++)
		{
			writeFloat(value[i]);
		}
	}

	/**
	 * ��ȡ��������ֵ����
	 * 
	 * @param ����������
	 *            �������ȡʧ���򷵻ؿ�
	 */
	public float[] readFloats()
	{
		int length = readInt();
		if (length < 0)
		{
			return null;
		}
		float[] value = new float[length];
		for (int i = 0; i < length; i++)
		{
			value[i] = readFloat();
		}
		return value;
	}

	/**
	 * д��������ֵ
	 * 
	 * @param value
	 *            ������ֵ
	 */
	public void writeBoolean(boolean value)
	{
		m_content+=value ? 1 : 0;
		m_content+=SEP;
	}

	/**
	 * ��ȡ������ֵ
	 * 
	 * @param value
	 *            ������ֵ
	 */
	public boolean readBoolean()
	{
		boolean value = false;
		String chunck = nextChunck();
		if (chunck != null)
		{
			try
			{
				int num = Integer.parseInt(chunck);
				value = num > 0;
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return value;
	}

	/**
	 * д����������
	 * 
	 * @param value
	 *            ��������
	 */
	public void writeBooleans(boolean value[])
	{
		if (value == null)
		{
			return;
		}
		writeInt(value.length);
		for (int i = 0; i < value.length; i++)
		{
			writeBoolean(value[i]);
		}
	}

	/**
	 * ��ȡ������ֵ����
	 * 
	 * @param ������ֵ����
	 *            �������ȡʧ���򷵻ؿ�
	 */
	public boolean[] readBooleans()
	{
		int length = readInt();
		if (length < 0)
		{
			return null;
		}
		boolean[] value = new boolean[length];
		for (int i = 0; i < length; i++)
		{
			value[i] = readBoolean();
		}
		return value;
	}

	/**
	 * д���ַ�����ֵ
	 * 
	 * @param value
	 *            �ַ�����ֵ
	 */
	public void writeString(String value)
	{
		m_content+=value;
		m_content+=SEP;
	}

	/**
	 * ��ȡ�ַ�����ֵ�������ȡʧ�ܣ��᷵��null
	 * 
	 * @return ������ֵ
	 */
	public String readString()
	{
		return nextChunck();
	}

	/**
	 * д���ַ�������ֵ����
	 * 
	 * @param value
	 *            �ַ�����ֵ����
	 */
	public void writeStrings(String value[])
	{
		if (value == null)
		{
			return;
		}
		writeInt(value.length);
		for (int i = 0; i < value.length; i++)
		{
			writeString(value[i]);
		}
	}

	/**
	 * ��ȡ�ַ�����ֵ����
	 * 
	 * @param �ַ�������
	 *            �������ȡʧ���򷵻ؿ�
	 */
	public String[] readStrings()
	{
		int length = readInt();
		if (length < 0)
		{
			return null;
		}
		String[] value = new String[length];
		for (int i = 0; i < length; i++)
		{
			value[i] = readString();
		}
		return value;
	}
	
	/**
	 * ��ȡ��һ���ı�����
	 * 
	 * @return ��һ���ı�����
	 */
	private String nextChunck()
	{
		int len = m_content.length();
		if (m_readPos >= len)
		{
			return null;
		}
		int nextEnd = m_content.indexOf(SEP, m_readPos);
		if (nextEnd < 0)
		{
			return null;
		}
		String chunck = m_content.substring(m_readPos, nextEnd);
		m_readPos = nextEnd + 1;
		return chunck;
	}
	/**
	 * �Ƿ������ݿ��Զ�ȡ
	 * 
	 * @return �Ƿ������ݿ��Զ�ȡ
	 */
	public boolean canRead()
	{
		int len = m_content.length();
		if (m_readPos >= len)
		{
			return false;
		}
		return true;
	}
	/**
	 * ���ͷ�����Ƿ���ָ�����ַ�����ע��˹����л���ǰ��ȡһ���ַ�������ȡλ��Ҳ�ᷢ���仯��
	 * ����ٴε��ô˺�����ʧЧ���������ֵ��ȡλ�á�ͷ�������ڱ����ͬID����£���ͬ�ļ�¼��
	 * 
	 * @return �Ƿ���ָ�����ַ���
	 */
	public boolean checkHeadData(String data)
	{
		if(!canRead()||data==null||data.length()==0||m_readPos!=0)
		{
			return false;
		}
		String next= readString();
		if(next==null||next.length()==0)
		{
			return false;
		}
		return next.equals(data);
	}
	/**
	 * ���ö�ȡλ�á�������ȡλ�ù��㣬��ͷ��ʼ��ȡ��
	 * �������Ҫ��ζ�ȡͬһ����¼�Ļ�����Ҫʹ�õ����������
	 */
	public void resetReadPos()
	{
		m_readPos=0;
	}
	/**
	 * ����������ݣ������ö�ȡλ�á�
	 */
	public void clearContent()
	{
		m_readPos=0;
		m_content="";
	}
	/**
	 * ����������ݣ������ö�ȡλ�á���д��ͷ���ݡ�
	 * ͷ�������ڱ����ͬID����£���ͬ�ļ�¼��
	 * @param head ͷ����
	 */
	public void clearContent(String head)
	{
		m_readPos=0;
		m_content="";
		writeString(head);
	}
	/**
	 * ��ʾ��¼����
	 */
	public void logDetail()
	{
		C2D_MiscUtil.log("m_recordID:"+m_recordID+",m_desc:"+m_desc+",m_content:"+m_content);
	}
}